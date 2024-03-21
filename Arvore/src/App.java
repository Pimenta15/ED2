public class App {
    public static void main(String[] args) throws Exception {
        AVLTree<Integer> arvore = new AVLTree<>();

        // Inserir alguns valores
        int[] inserir = {63,27,85,15,34,81,97,5,19,30,45,90,99,3};
        int[] remover = {27, 63, 34,30};
           
            System.out.println("Inserir"); 
        for (int valor : inserir) {
            arvore.insert(valor);
            arvore.passeioPorNivel();
            System.out.println();
        }
        System.out.println("NivelL");
        arvore.passeioPorNivel();
        System.out.println("\nOrdemL");
        arvore.Ordem();
        System.out.println();
        
            System.out.println("Remover:");
        for (int valor : remover) {
            arvore.removeNode(arvore.getRoot(),valor);
            arvore.passeioPorNivel();
            System.out.println();
        }

        // Exibir a árvore usando percurso por nível
        System.out.println("NivelR");
        arvore.passeioPorNivel();
        System.out.println("\nOrdemR");
        arvore.Ordem();
    }
}
