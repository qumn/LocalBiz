package xyz.qumn.lb.management.api.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.locationtech.jts.geom.Geometry;

import java.io.IOException;

public class PointToJsonSerializer extends JsonSerializer<Geometry> {
    @Override
    public void serialize(Geometry value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        try {
            if (value != null) {
                double lat = value.getCoordinate().getX();
                double lon = value.getCoordinate().getY();
                gen.writeStartObject();
                gen.writeObjectField("x", lat);
                gen.writeObjectField("y", lon);
                gen.writeEndObject();
                return;
            }
        } catch (Exception e) {
            // 不处理
        }
        gen.writeNull();
    }
}
