# Hash Table Extensível em Java

## Descrição

Este projeto implementa uma Hash Table Dinâmica Extensível, uma estrutura de dados que permite crescimento e redução dinâmica conforme a quantidade de elementos armazenados.

A implementação inclui:
- Inserção de chaves
- Busca de valores
- Remoção de elementos
- Divisão de buckets (split)
- Fusão de buckets (merge)
- Geração de visualização da estrutura utilizando arquivos `.dot`

## Funcionamento

A estrutura é composta por dois elementos principais:

### Diretório
- Vetor que aponta para os buckets
- Seu tamanho é definido por 2^globalDepth
- Utiliza os bits menos significativos da chave para indexação

### Buckets
- Armazenam as chaves
- Possuem capacidade limitada
- Cada bucket possui uma profundidade local (localDepth)

## Operações

### Inserção
- Calcula o índice utilizando a função hash
- Insere a chave no bucket correspondente
- Se o bucket estiver cheio:
  - O bucket é dividido (split)
  - O diretório pode ser expandido

### Busca
- Calcula o índice da chave
- Verifica se a chave está presente no bucket

### Remoção
- Remove a chave do bucket
- Se o bucket ficar vazio:
  - Pode ocorrer fusão (merge) com bucket vizinho
  - O diretório pode ser reduzido

## Visualização da Estrutura

O sistema gera arquivos `.dot` que representam:
- Os índices do diretório (em binário)
- Os buckets
- As conexões entre diretório e buckets

Esses arquivos podem ser convertidos em imagens `.svg` utilizando o Graphviz.

## Como executar

### 1. Compilar o projeto

No terminal, dentro da pasta do projeto:

javac *.java

### 2. Executar

java Main

Serão gerados arquivos `.dot`, como:
- caso1.dot
- caso2.dot
- caso3.dot
- caso4.dot
- caso5.dot

## Gerar visualização (.svg)

Após instalar o Graphviz, execute no terminal:

`cd "C:\Program Files\Graphviz\bin"`

Depois:

- `.\dot -Tsvg dotFiles\caso1.dot -o svgFiles\caso1.svg`
- `.\dot -Tsvg dotFiles\caso2.dot -o svgFiles\caso2.svg`
- `.\dot -Tsvg dotFiles\caso3.dot -o svgFiles\caso3.svg`
- `.\dot -Tsvg dotFiles\caso4.dot -o svgFiles\caso4.svg`
- `.\dot -Tsvg dotFiles\caso5.dot -o svgFiles\caso5.svg`

## Casos de teste

O projeto inclui cinco cenários principais:

1. Inserção simples
2. Split de bucket
3. Expansão do diretório
4. Remoção sem merge
5. Remoção com tentativa de merge

## Observações

- A função hash utiliza máscara de bits baseada na profundidade global
- Um mesmo bucket pode ser referenciado por múltiplos índices
- A estrutura se adapta dinamicamente ao volume de dados

## Autor

Trabalho acadêmico desenvolvido para a disciplina de Algoritmos e Programação: Estruturas Avançadas de Dados por Gustavo R. Schwert e Nicolas D. Silveira.
