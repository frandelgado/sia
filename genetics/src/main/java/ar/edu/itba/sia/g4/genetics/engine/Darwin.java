package ar.edu.itba.sia.g4.genetics.engine;

import ar.edu.itba.sia.g4.genetics.problem.Combinator;
import ar.edu.itba.sia.g4.genetics.problem.Couple;
import ar.edu.itba.sia.g4.genetics.problem.EvolutionaryTarget;
import ar.edu.itba.sia.g4.genetics.problem.Mutator;
import ar.edu.itba.sia.g4.genetics.problem.Species;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Darwin<T extends Species> implements GeneticEngine<T> {
    private final EvolutionaryTarget<T> target;
    private final Combinator<T> combinator;
    private final Mutator<T> mutator;
    private final Replacer<T> replacer;
    private final static Logger logger = LoggerFactory.getLogger(Darwin.class);
    private final List<Inspector<T>> inspectors = new LinkedList<>();

    public Darwin(EvolutionaryTarget<T> target, Combinator<T> combinator, Mutator<T> mutator, Replacer<T> replacer) {
        this.target = target;
        this.combinator = combinator;
        this.mutator = mutator;
        this.replacer = replacer;
    }

    public Inspector<T> attachInspector(Inspector<T> inspector) {
        if (!inspectors.contains(inspector)) {
            this.inspectors.add(inspector);
        }
        return inspector;
    }

    public Inspector<T> detachInspector(Inspector<T> inspector) {
        if (inspectors.contains(inspector)) {
            inspectors.remove(inspector);
        }
        return inspector;
    }

    public List<T> evolve(List<T> population) {
        List<T> previousPopulation;
        List<T> currentPopulation = population;
        long generation = 0;
        do {
            previousPopulation = currentPopulation;
            currentPopulation = getNextGeneration(currentPopulation, generation);
            assert previousPopulation.size() == currentPopulation.size();
            inspectGeneration(previousPopulation, currentPopulation, generation);
        } while (target.shouldEvolve(previousPopulation, currentPopulation, generation++));
        return currentPopulation;
    }

    private void inspectGeneration(List<T> prev, List<T> cur, long generation) {
        logger.debug("Forwarding to inspectors");
        inspectors.forEach(inspector -> inspector.onGeneration(prev, cur, generation));
    }

    private List<T> getNextGeneration(List<T> population, long generation) {
        // do some math to find the correct population gap
        int k = (int)(replacer.getGenerationalGapRatio() * population.size());

        // select some k parents
        List<T> parents = replacer.getParents(population, k, generation);
        // make some couples (k / 2 + 1)
        List<T> children = combinator.pickCouples(parents, k / 2 + 1)
         .stream().parallel()
        // see if we should breed the couples. Breed them or return them
         .map(p -> breedPair(p, generation))
         // Note: if we had K pairs and each *sort preserving* pair only produced a single
         // offspring, the flatMap wouldn't be necessary and this would be a tad faster.
         // The only property we need to ensure that is that if X({p1, p2}) -> ({c1, c2})
         // then X'(p1, p2) -> c1 and X'(p2, p1) -> c2
         .flatMap(pair -> Stream.of(pair.getHead(), pair.getTail()))
        // see if we should mutate individuals. Mutate if so
         .map(i -> mutateThing(i, generation))
        // limit k
         .limit(k)
         .collect(Collectors.toList());
        // use replacement method (#N, #k) -> #N
        return replacer.mix(population, children, generation);
    }
    
    private Couple<T> breedPair(Couple<T> couple, long generation) {
        return combinator.shouldBreed(couple, generation) ? combinator.breed(couple) : couple;
    }
    
    private T mutateThing(T theThing, long generation) {
        return mutator.shouldMutate(theThing, generation) ? mutator.mutate(theThing) : theThing;
    }
}
