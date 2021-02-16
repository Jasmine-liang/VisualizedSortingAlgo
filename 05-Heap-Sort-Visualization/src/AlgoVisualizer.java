
import java.awt.*;


public class AlgoVisualizer {

    private static int DELAY = 20;

    private HeapSortData data;
    private AlgoFrame frame;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N){

        // 初始化数据
        data = new HeapSortData(N, sceneHeight);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Heap Sort Visualization", sceneWidth, sceneHeight);

            new Thread(() -> {
                run();
            }).start();
        });
    }

    public void run(){

        setData(data.N());

        // 建堆
        //Heapify: Find the first node that is not a leaf, then we start shifting down with this node
        //When a heap starts from index 1, the first node(that is not a leaf) = (heap.length) / 2
        /*
            for(int i = heap.length/2; i >=1; i--){
                shiftDown(i);
            }
         */
        for( int i = (data.N()-1-1)/2 ; i >= 0 ; i -- ){
            shiftDown(data.N(), i);
        }

        // 堆排序
        for( int i = data.N()-1; i > 0 ; i-- ){
            //The first node of heap is the biggest number, so we swap
            data.swap(0, i);
            shiftDown(i, 0);
            setData(i);
        }

        setData(0);
    }

    //Extract number from heap
    private void shiftDown(int n, int k){
        //when a node has left child
        while( 2*k+1 < n ){
            int j = 2*k+1;
            //If right child exists and rigth child > left child, we increase j to represent the bigger number
            if( j+1 < n && data.get(j+1) > data.get(j) )
                //Now the right child is the bigger number
                j += 1;

            //If node > both childs, there's no need to change the node, just break the loop
            if( data.get(k) >= data.get(j) )
                break;
            //Otherwise, we change the node to the bigger number(index j)
            data.swap(k, j);
            setData(data.heapIndex);
            //Thus we shift the node down
            k = j;
        }
    }

    private void setData(int heapIndex){

        data.heapIndex = heapIndex;

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 100;

        AlgoVisualizer vis = new AlgoVisualizer(sceneWidth, sceneHeight, N);
    }
}