package xyz.qumn.lb.management.api.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;

import java.io.IOException;

public class PointToJsonSerializer extends JsonSerializer<Point<G2D>> {
    @Override
    public void serialize(Point value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        try {
            if (value != null) {
                double lat = value.getPosition().getCoordinate(0);
                double lon = value.getPosition().getCoordinate(1);
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
