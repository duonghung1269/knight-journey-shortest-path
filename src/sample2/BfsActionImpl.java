//package sample2;
//
//import java.util.HashMap;
//
//import sample2.BfsAlgorithm.BfsAction;
//
//public class BfsActionImpl implements BfsAction {
//
//	private Vertex currParent;
//	private HashMap<Vertex, Vertex> parentToChild;
//
//    @Override
//    public boolean onVisitParent(Vertex parent) {
//        currParent = parent;
//        return false;
//    }
//
//    @Override
//    public boolean onVisitChild(Vertex vertex) {
//        parentToChild.put(vertex, currParent);
//        if (vertex.equals(to)) return true;
//        else return false;
//    }
//
//}
