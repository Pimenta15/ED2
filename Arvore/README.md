public No delete(No no, Integer chave) {
        if (no == null) return null;

        if (chave < no.chave)
            no.esquerda = delete(no.esquerda, chave);
        else if (chave > no.chave)
            no.direita = delete(no.direita, chave);
        else {
            if (no.esquerda != null && no.direita != null) {
                No maximoNoParaEsquerda = Max(no.esquerda);
                no.chave = maximoNoParaEsquerda.chave;
                no.esquerda = delete(no.esquerda, maximoNoParaEsquerda.chave);
            }

            else if (no.esquerda != null) no = no.esquerda;
            else if (no.direita != null) no = no.direita;
            else no = null;

        }
        int valor = balancear(no);
        //LL
        if (valor < -1) {
            assert no != null;
            if (balancear(no.esquerda) <= 0)
                return rotacaoDireita(no);
        }

        //RR
        if (valor > 1) {
            assert no != null;
            if (balancear(no.direita) >= 0)
                return rotacaoEsquerda(no);
        }

        //LR
        if (valor < -1 && balancear(no.esquerda) > 0){
            no.esquerda = rotacaoEsquerda(no.esquerda);
            return rotacaoDireita(no);
        }

        //RL
        if (valor > 1 && balancear(no.direita) < 0) {
            no.direita = rotacaoDireita(no.direita);
            return rotacaoEsquerda(no);
        }

        return no;
    }