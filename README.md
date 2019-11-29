# Artificial Intelligence Systems
This repository contains three projects. These projects demonstrate diverse types of Artificial intelligence systems.

## Genetic algorithms
Found under the genetics folder, the project aimed to find the best possible armor set for a given character using genetic algorithms. Characters had thousands of items to chose from.

The project was implemented with a recurring pipeline. This pipeline had as input the entire population at generation n and outputed the generation at n+1

### The pipeline:
- Replacer: gets the parents wchich will breed
- combinator: picks couples
- combinator: breedss couples
- mutator: mutates offsprings
- Replacer: mixes offspring and old population

## Neural networks
This project implemented a multiplayered neural network wchich had to generalize a terrain based on certain place and height measurements.

The neural network implemented and offered several features such as adaptative eta and momentum.

## Search
This project implements a solver for the rolling cubes game. Several heuristics were implemented and a cost function was constructed. 

### The search algorithms implemented where:

- Breath first search
- Depth first search
- Iterative deepening depth first search
- A star
- Greedy


