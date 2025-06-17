package com.algaworks.algacomments.device.comments.common;

import io.hypersistence.tsid.TSID;

import java.util.Optional;

public class GeneratorID {

    private static final String TSID_NODE = "tsid.node";
    private static final String TSID_NODE_COUNT = "tsid.node.count";
    private static final TSID.Factory tsidFactory;

    static {
        Optional.ofNullable(System.getenv(TSID_NODE))
                .ifPresent(tsiNode -> System.setProperty(TSID_NODE, tsiNode));
        Optional.ofNullable(System.getenv(TSID_NODE_COUNT))
                .ifPresent(tsiNodeCount -> System.setProperty(TSID_NODE, tsiNodeCount));
        tsidFactory = TSID.Factory.builder().build();
    }

    private GeneratorID() {
    }

    public static TSID generateTSID() {
        return tsidFactory.generate();
    }

}
