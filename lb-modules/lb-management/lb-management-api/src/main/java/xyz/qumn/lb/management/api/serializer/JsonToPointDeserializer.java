package xyz.qumn.lb.management.api.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.DoubleNode;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.io.WKTReader;

public class JsonToPointDeserializer extends JsonDeserializer<Geometry> {

    final static WKTReader WKTREADER = new WKTReader();
    @Override
    public Geometry deserialize(JsonParser jp, DeserializationContext ctxt) {
        try {
            // parse {x: 1.0, y: 2.0}
            TreeNode node = jp.getCodec().readTree(jp);
            double y = (double) ((DoubleNode) node.get("y")).numberValue();
            double x = (double) ((DoubleNode) node.get("x")).numberValue();
            Geometry geometry = WKTREADER.read("POINT(" + x + " " + y + ")");
            return geometry;
        } catch (Exception e) {
            return null;
        }
    }
}