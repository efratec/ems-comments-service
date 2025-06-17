package com.algaworks.algacomments.device.comments.api.config.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import io.hypersistence.tsid.TSID;

import java.io.IOException;

public class StringToTSIDDeserializer extends JsonDeserializer<TSID> {

    @Override
    public TSID deserialize(JsonParser parser, DeserializationContext deserializationContext)
            throws IOException {
        return TSID.from(parser.getText());
    }

}
