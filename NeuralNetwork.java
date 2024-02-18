import java.util.Scanner;

class NeuralNetwork {
    private int numLayers;
    private int[] nodesInLayer;
    private double[][] edgeWeights;

    public NeuralNetwork(int numLayers, int[] nodesInLayer) {
        this.numLayers = numLayers;
        this.nodesInLayer = nodesInLayer;
        this.edgeWeights = new double[numLayers - 1][];
        for (int i = 0; i < numLayers - 1; i++) {
            edgeWeights[i] = new double[nodesInLayer[i] * nodesInLayer[i + 1]];
        }
    }

    public void setEdgeWeight(int layer, int nodeFrom, int nodeTo, double weight) {
        int index = nodeFrom * nodesInLayer[layer + 1] + nodeTo;
        edgeWeights[layer][index] = weight;
    }

    public double getEdgeWeight(int layer, int nodeFrom, int nodeTo) {
        int index = nodeFrom * nodesInLayer[layer + 1] + nodeTo;
        return edgeWeights[layer][index];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of layers: ");
        int numLayers = scanner.nextInt();

        int[] nodesInLayer = new int[numLayers];
        for (int i = 0; i < numLayers; i++) {
            System.out.print("Enter the number of nodes in layer " + (i + 1) + ": ");
            nodesInLayer[i] = scanner.nextInt();
        }

        NeuralNetwork neuralNetwork = new NeuralNetwork(numLayers, nodesInLayer);

        for (int layer = 0; layer < numLayers - 1; layer++) {
            for (int nodeFrom = 0; nodeFrom < nodesInLayer[layer]; nodeFrom++) {
                for (int nodeTo = 0; nodeTo < nodesInLayer[layer + 1]; nodeTo++) {
                    System.out.print("Enter weight for edge from node " + nodeFrom +
                            " in layer " + layer + " to node " + nodeTo + " in layer " + (layer + 1) + ": ");
                    double weight = scanner.nextDouble();
                    neuralNetwork.setEdgeWeight(layer, nodeFrom, nodeTo, weight);
                }
            }
        }

        System.out.println("Edge weights set successfully!");
        int queryLayer = 1;
        int queryNodeFrom = 0; 
        int queryNodeTo = 1; 
        double queriedWeight = neuralNetwork.getEdgeWeight(queryLayer, queryNodeFrom, queryNodeTo);
        System.out.println("Weight of edge from node " + queryNodeFrom +
                " in layer " + queryLayer + " to node " + queryNodeTo + ": " + queriedWeight);
    }
}
