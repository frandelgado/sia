package ar.edu.itba.sia.g4.search.solver;

import ar.com.itba.sia.Heuristic;
import ar.com.itba.sia.Problem;
import ar.com.itba.sia.Rule;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SolverTest {
    private MockedState initialState = new MockedState();
    private MockedState finalState = new MockedState();
    private MockedState stateA = new MockedState();
    private MockedState stateB = new MockedState();
    private MockedState stateX1 = new MockedState();
    private MockedState stateX2 = new MockedState();
    private MockedState stateX3 = new MockedState();
    private MockedState stateX4 = new MockedState();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    private Heuristic<MockedState> setUpHeuristic() {
        Heuristic<MockedState> heuristic = mock(Heuristic.class);
        when(heuristic.getValue(initialState)).thenReturn(3.);
        when(heuristic.getValue(finalState)).thenReturn(0.);
        when(heuristic.getValue(stateA)).thenReturn(2.);
        when(heuristic.getValue(stateB)).thenReturn(1000.);
        when(heuristic.getValue(stateX1)).thenReturn(2000.);
        when(heuristic.getValue(stateX2)).thenReturn(2000.);
        when(heuristic.getValue(stateX3)).thenReturn(2000.);
        when(heuristic.getValue(stateX4)).thenReturn(2000.);
        return heuristic;
    }

    private Rule<MockedState>[] mockRules() {
        Rule<MockedState>[] result = new Rule[] {
         mock(Rule.class), // f: I -> A
         mock(Rule.class), // f: I -> B
         mock(Rule.class), // f: B -> X1
         mock(Rule.class), // f: B -> X2
         mock(Rule.class), // f: A -> X3
         mock(Rule.class), // f: A -> X4
         mock(Rule.class), // f: A -> F
        };

        IntStream.range(0, result.length).forEach(i -> when(result[i].getCost()).thenReturn(1.));
        when(result[0].applyToState(initialState)).thenReturn(stateA);
        when(result[1].applyToState(initialState)).thenReturn(stateB);
        when(result[2].applyToState(stateB)).thenReturn(stateX1);
        when(result[3].applyToState(stateB)).thenReturn(stateX2);
        when(result[4].applyToState(stateA)).thenReturn(stateX3);
        when(result[5].applyToState(stateA)).thenReturn(stateX4);
        when(result[6].applyToState(stateA)).thenReturn(finalState);
        return result;
    }

    private Problem<MockedState> setUpProblem() {
        Rule[] rules = mockRules();
        Problem<MockedState> problem = mock(Problem.class);
        when(problem.getInitialState()).thenReturn(initialState);
        when(problem.getRules(initialState)).thenReturn(Arrays.asList(rules[0], rules[1]));
        when(problem.getRules(stateA)).thenReturn(Arrays.asList(rules[4], rules[5], rules[6]));
        when(problem.getRules(stateB)).thenReturn(Arrays.asList(rules[2], rules[3]));
        when(problem.getRules(stateX1)).thenReturn(Collections.emptyList());
        when(problem.getRules(stateX2)).thenReturn(Collections.emptyList());
        when(problem.getRules(stateX3)).thenReturn(Collections.emptyList());
        when(problem.getRules(stateX4)).thenReturn(Collections.emptyList());
        when(problem.getRules(finalState)).thenReturn(Collections.emptyList());
        when(problem.isResolved(initialState)).thenReturn(false);
        when(problem.isResolved(stateA)).thenReturn(false);
        when(problem.isResolved(stateB)).thenReturn(false);
        when(problem.isResolved(stateX1)).thenReturn(false);
        when(problem.isResolved(stateX2)).thenReturn(false);
        when(problem.isResolved(stateX3)).thenReturn(false);
        when(problem.isResolved(stateX4)).thenReturn(false);
        when(problem.isResolved(finalState)).thenReturn(true);


        return problem;
    }

    private void testForInvariants(Node<MockedState> finalNode) {
        assertThat("Final cost", finalNode.getCost(), equalTo(2.));
        assertThat("Final node", finalNode.getState(), equalTo(finalState));
        assertThat("Path", finalNode.getParent().getState(), equalTo(stateA));
        assertThat("Path", finalNode.getParent().getParent().getState(), equalTo(initialState));
    }

    @Test
    public void shouldSolveWithBFS() {
        Problem<MockedState> problem = setUpProblem();
        SearchStrategy<MockedState> backend = new BFSStragegy<>();
        Solver<MockedState> solver = new Solver<>(problem, backend);

        Node<MockedState> finalNode = solver.solve();
        testForInvariants(finalNode);
        // Queue I, then A, then B, Then X1, 2, and F
        assertThat("Queued nodes", finalNode.getQueuedNodes(), equalTo(8));
        assertThat("Visited nodes", finalNode.getVisitedNodes(), equalTo(6));
    }

    @Test
    public void shouldSolveWithDFS() {
        Problem<MockedState> problem = setUpProblem();
        SearchStrategy<MockedState> backend = new DFSStragegy<>();
        Solver<MockedState> solver = new Solver<>(problem, backend);

        Node<MockedState> finalNode = solver.solve();
        testForInvariants(finalNode);
        assertThat("Queued nodes", finalNode.getQueuedNodes(), equalTo(6));
        assertThat("Visited nodes", finalNode.getVisitedNodes(), equalTo(5));
    }

    @Test
    public void shouldSolveWithAStar() {
        Problem<MockedState> problem = setUpProblem();
        Heuristic<MockedState> heuristic = setUpHeuristic();
        SearchStrategy<MockedState> backend = new AStarStrategy<>(heuristic);
        Solver<MockedState> solver = new Solver<>(problem, backend);
        solver.subscribe(node -> System.out.println(node.getState()));

        Node<MockedState> finalNode = solver.solve();
        testForInvariants(finalNode);

    }
}
