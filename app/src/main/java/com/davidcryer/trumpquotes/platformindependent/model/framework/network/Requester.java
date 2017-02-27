package com.davidcryer.trumpquotes.platformindependent.model.framework.network;

import com.davidcryer.trumpquotes.platformindependent.model.framework.Cancelable;

public interface Requester<CallbackType extends RequestCallback> {
    Cancelable request(final CallbackType callback);
}
