package com.davidcryer.mvpandroid.platformindependent.javahelpers;

public class ThrowableHelper {
    private final static String MUST_EXTEND = " must extend ";
    private final static String MUST_IMPLEMENT = " must implement ";

    public static ClassCastException getClassCastExceptionForCastToInterface(
            final String castedObjectName,
            final String castType
    ) {
        return getClassCastException(castedObjectName, castType, true);
    }

    public static ClassCastException getClassCastExceptionForCastToClass(
            final String castedObjectName,
            final String castType
    ) {
        return getClassCastException(castedObjectName, castType, false);
    }

    private static ClassCastException getClassCastException(
            final String castedObjectName,
            final String castType,
            final boolean castTypeIsInterface
    ) {
        return new ClassCastException(castedObjectName + (castTypeIsInterface ? MUST_IMPLEMENT : MUST_EXTEND) + castType);
    }
}
