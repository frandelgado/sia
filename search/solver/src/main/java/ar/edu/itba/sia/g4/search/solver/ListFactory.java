package ar.edu.itba.sia.g4.search.solver;

public class ListFactory {

    public static GenericList<Node> getList(int type){
        if(type == 0){
            return new Stack<>();
        }
        if(type == 1){
            return new Queue<>();
        }
        if(type == 2){
            return new PQueue<>();
        }
        else throw new IllegalArgumentException("list type not supported");
    }
}
