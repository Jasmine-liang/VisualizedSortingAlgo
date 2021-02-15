import java.awt.*;


public class AlgoVisualizer {

    // TODO: 创建自己的数据
    private InsertionSortData data;        // 数据
    private AlgoFrame frame;    // 视图
    private static int DELAY = 20;
    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N){

        // 初始化数据
        // TODO: 初始化数据
        data = new InsertionSortData(N, sceneHeight);
        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Insertion Sort Visualization", sceneWidth, sceneHeight);

            new Thread(() -> {
                run();
            }).start();
        });
    }

    // 动画逻辑
    private void run(){

        setData(0, -1);
        for (int i = 0; i < data.N(); i++) {
            setData(i, i);

            //Compared to the previous numbers
            //If the data[j-1] > data[j], swap these two numbers
            for (int j = i; j > 0 && data.get(j) < data.get(j - 1);  j--){
                data.swap(j, j-1);
                setData(i+1, j-1);
            }
        }
        setData(data.N(), -1);
    }

    private void setData(int orderedIndex, int currentIndex){
        data.orderedIndex = orderedIndex;
        data.currentIndex = currentIndex;

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }
    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 100;

        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, N);
    }
}
