package ru.job4j.ood.lsp;

/**
 * Нарушение контракта LSP на примере, где предусловие было усилено в подклассе.
 * Параметр score в родительском классе должен быть больше 4 и введенное число в диапазоне 1-6.
 * Когда в классе наследнике ослабляется предварительное условие,
 * он ослабляет ограничения, налагаемые в классе родителя.
 */
public class ReportCard {
    protected double score;

    public ReportCard(double score) {
        this.score = score;
    }

    public void check(int num) {
        if (num <= 0 || num > 6) {
            throw new IllegalArgumentException("Input out of range 1-6");
        }
        if (score < 4) {
            throw new IllegalArgumentException("You don't have enough score");
        }

        class ReportCardForDepartment extends ReportCard {


            public ReportCardForDepartment(double score) {
                super(score);
            }

            public void check(int num) {
                if (num <= 0 || num > 7) {
                    throw new IllegalArgumentException("Input out of range 1-7");
                }
                if (score < 5) {
                    throw new IllegalArgumentException("You don't have enough score");
                }
            }
        }
    }
}
