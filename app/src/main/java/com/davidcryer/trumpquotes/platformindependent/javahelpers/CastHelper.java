package com.davidcryer.trumpquotes.platformindependent.javahelpers;

public class CastHelper {

    public static <ReturnType> ReturnType riskyCastToInterface(Object castedObject, Class<ReturnType> returnTypeClass) {
        try {
            return returnTypeClass.cast(castedObject);
        } catch (ClassCastException cce) {
            throw ThrowableHelper.getClassCastExceptionForCastToInterface(castedObject.toString(), returnTypeClass.getSimpleName());
        }
    }
}
