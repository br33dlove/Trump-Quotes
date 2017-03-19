package com.davidcryer.trumpquotes.platformindependent.model.domainentities;

public class QuizAnswer {
    private final static String INIT_OPTION_A = "a";
    private final static String INIT_OPTION_B = "b";
    private enum AnswerType {
        OPTION_A {
            @Override
            String initType() {
                return INIT_OPTION_A;
            }

        },
        OPTION_B {
            @Override
            String initType() {
                return INIT_OPTION_B;
            }
        };
        abstract String initType();
    }
    private final AnswerType answerType;

    private QuizAnswer(final AnswerType answerType) {
        this.answerType = answerType;
    }

    static QuizAnswer newInstance(final String initString) {
        switch (initString) {
            case INIT_OPTION_A: {
                return newInstanceA();
            }
            case INIT_OPTION_B: {
                return newInstanceB();
            }
            default: {
                return null;
            }
        }
    }

    public static QuizAnswer newInstanceA() {
        return new QuizAnswer(AnswerType.OPTION_A);
    }

    public static QuizAnswer newInstanceB() {
        return new QuizAnswer(AnswerType.OPTION_B);
    }

    boolean matches(final QuizAnswer answer) {
        return this.answerType == answer.answerType;
    }

    String answerType() {
        return answerType.initType();
    }
}
