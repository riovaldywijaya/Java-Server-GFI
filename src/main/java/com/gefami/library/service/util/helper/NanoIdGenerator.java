package com.gefami.library.service.util.helper;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.Random;

public class NanoIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        return NanoIdUtils.randomNanoId(new Random(), NanoIdUtils.DEFAULT_ALPHABET, 21);
    }
}
