public class App {
    public static void main(String[] args) throws Exception {
        AVLTree<Integer> arvore = new AVLTree<>();

        // Inserir alguns valores
        int[] valores = { 11, 20, 32, 43, 50, 61, 70, 99, 98, 97, 95, 93, 92, 91, 80};
        for (int valor : valores) {
            arvore.insert(valor);
            arvore.levelOrder();
            System.out.println();
        }

        // Exibir a árvore usando percurso por nível
        System.out.println("Nivel");
        arvore.levelOrder();
        System.out.println("\nOrdem");
        arvore.inOrder();
    }
}
