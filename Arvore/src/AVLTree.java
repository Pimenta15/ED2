import java.util.LinkedList;
import java.util.Queue;



public class AVLTree<T extends Comparable<T>> {

    private AVLNode<T> root;

    private boolean status;
    
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public AVLNode<T> getRoot() {
        return root;
    }

    public void setRoot(AVLNode<T> root) {
        this.root = root;
    }

    // Código dos métodos aqui

    private boolean isEmpty() {
        return root == null;
    }

    public void insert(T value) {
        if (this.isEmpty() == true) {
            this.root = new AVLNode<T>(value);
            this.status = true;
        } else {
            this.root = insertNode(this.root, value);
        }
    }

    private AVLNode<T> insertNode(AVLNode<T> r, T value) {
        if (r == null) {
            r = new AVLNode<T>(value);
            this.status = true;
        } else if (r.getInfo().compareTo(value) > 0) {
            r.setLeft(insertNode(r.getLeft(), value));
            if (this.status == true) {
                switch (r.getFatBal()) {
                    case 1:
                        r.setFatBal(0);
                        this.status = false;
                        break;
                    case 0:
                        r.setFatBal(-1);
                        break;
                    case -1:
                        r = this.rotateRight(r);
                        break;
                } // fim switch
            } // fim if
        } // fim if
        else {
            r.setRight(insertNode(r.getRight(), value));
            if (this.status == true) {
                switch (r.getFatBal()) {
                    case -1:
                        r.setFatBal(0);
                        this.status = false;
                        break;
                    case 0:
                        r.setFatBal(1);
                        break;
                    case 1:
                        r = this.rotateLeft(r);
                        break;
                } // fim switch
            } // fim if
        } // fim else
        return r;
    } // fim insertNode

    private AVLNode<T> rotateLeft(AVLNode<T> a) {
        AVLNode<T> b = a.getRight();
        // rotação dupla
        if (b.getFatBal() == -1) {
            AVLNode<T> c = b.getLeft();
            a.setRight(c.getLeft());
            b.setLeft(c.getRight());
            c.setRight(b);
            c.setLeft(a);
            // fatBal
            if (c.getFatBal() == 1) {
                a.setFatBal(-1);
            } else {
                a.setFatBal(0);
            }
            if (c.getFatBal() == -1) {
                b.setFatBal(1);
            } else {
                b.setFatBal(0);
            }
            c.setFatBal(0);
            this.status = false;
            return c;
        }
            // rotação simples
            a.setRight(b.getLeft());
            b.setLeft(a);
            // fatball
            a.setFatBal(0);
            b.setFatBal(0);
            this.status = false;
            return b;
        }
    

    private AVLNode<T> rotateRight(AVLNode<T> a) {
        AVLNode<T> b = a.getLeft();
        // rotação dupla
        if (b.getFatBal() == 1) {
            AVLNode<T> c = b.getRight();
            b.setRight(c.getLeft());
            a.setLeft(c.getRight());
            c.setRight(a);
            c.setLeft(b);
            // fatBal
            if (c.getFatBal() == -1) {
                a.setFatBal(1);
            } else {
                a.setFatBal(0);
            }
            if (c.getFatBal() == 1) {
                b.setFatBal(-1);
            } else {
                b.setFatBal(0);
            }
            c.setFatBal(0);
            this.status = false;
            return c;
        }
            // rotação simples
            a.setLeft(b.getRight());
            b.setRight(a);
            // fatball
            a.setFatBal(0);
            b.setFatBal(0);
            this.status = false;
            return b;

        }
    

    public void Ordem() {
        emOrdem(root);
    }

    private void emOrdem(AVLNode<T> node) {
        if (node != null) {
            emOrdem(node.getLeft());
            System.out.print(node.getInfo() +"/"+node.getFatBal()+ " ");
            emOrdem(node.getRight());
        }
    }

    public void passeioPorNivel() {
        if (root == null) {
            return;
        }
        Queue<AVLNode<T>> nodes = new LinkedList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            AVLNode<T> node = nodes.remove();
            if (node == null) {
                System.out.print("nulo ");
            } else {
                System.out.print(node.getInfo() +"/"+node.getFatBal()+ " ");
                nodes.add(node.getLeft());
                nodes.add(node.getRight());
            }
        }
    }

     public AVLNode<T> removeNode(AVLNode<T> r, T value) {
        if (r != null) {
            if (r.getInfo().compareTo(value)==0) {
            //Codigo de remoção
            if (r.getLeft() == null && r.getRight() ==null) {
                //caso 1(nenhum filho)
                r = null;
            }else if(r.getRight()!=null && r.getLeft()==null){
                //caso2a(1 filho a direita)
                r = r.getRight();
            }else if(r.getRight()==null && r.getLeft()!=null){
               //caso2a(1 filho a esquerda)
                r = r.getLeft();
            }else{
                //caso 3 (dois filhos)
                AVLNode<T> subTreeL;
                T maior;
                if (r == this.root) {
                    subTreeL = this.root.getLeft();
                    if (subTreeL.getRight() == null) {
                        maior = subTreeL.getInfo();
                    }else{
                        maior = descer(subTreeL,subTreeL).getInfo();
                    }
                    this.root.setInfo(maior);
                }else{
                    AVLNode<T> aux = r.getLeft();
                    aux = descer(aux,aux);
                    r.setInfo(aux.getInfo());
                    if (r.getLeft() == aux) {
                        r.setLeft(null);
                        switch (r.getFatBal()) {
                            case 0:
                                r.setFatBal(1);
                                break;
                            case 1:
                                r = this.rotateLeft(r);
                                break;
                            case -1:
                                r.setFatBal(0);
                                break;
                        }
                    }
                }
               
            }
            this.status = true;

            } else if ((r.getInfo().compareTo(value)>0)) {
                r.setLeft(removeNode(r.getLeft(), value));
                if (status) {
                    
                    switch (r.getFatBal()) {
                        case 1:
                            r = this.rotateLeft(r);
                            break;
                        case 0:
                            r.setFatBal(1);
                            
                            break;
                        case -1:
                            r.setFatBal(0);
                            break;
                    }
                }
            } else {
                r.setRight(removeNode(r.getRight(), value));
               if (status) {
                    
                    switch (r.getFatBal()) {
                        case 1:
                            r.setFatBal(0);
                            break;
                        case 0:
                            r.setFatBal(-1);
                            break;
                        case -1:
                            r = this.rotateRight(r);
                            break;
                    }
                }
            }
            
        }
        return r;
    }
    
    private AVLNode<T> descer(AVLNode<T> atual, AVLNode<T> maior){
        
        if (atual.getRight() !=null) {
            maior = descer(atual.getRight(),maior);
            
                
                switch (atual.getFatBal()) {
                    case 0:
                        atual.setFatBal(-1);
                        break;
                    case 1:
                        atual.setFatBal(0);
                        break;
                    case -1:
                        //atual = rotateRight(atual); 
                        break;
                
            }
        }
        if (atual.getRight()== null) {
            maior = atual;
        }
        if (atual.getRight() == maior) {
            if (maior.getLeft()!= null) {
                atual.setRight(maior.getLeft());
                
            }else{
                atual.setRight(null);
            }
        }
       
        return maior;
    }

}