package xyz.qumn.lb.management.api.serializer;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.DoubleNode;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.geolatte.geom.crs.CoordinateReferenceSystem;
import org.geolatte.geom.crs.CrsRegistry;

import java.io.IOException;

public class JsonToPointDeserializer extends JsonDeserializer<Point<G2D>> {

    private final static CoordinateReferenceSystem crs = CrsRegistry.getCoordinateReferenceSystemForEPSG(4326, null);

    @Override
    public Point<G2D> deserialize(JsonParser jp, DeserializationContext ctxt) {
        try {
            // parse {x: 1.0, y: 2.0}
            TreeNode node = jp.getCodec().readTree(jp);
            double y = (double) ((DoubleNode) node.get("y")).numberValue();
            double x = (double) ((DoubleNode) node.get("x")).numberValue();
            return new Point<G2D>(new G2D(x, y), crs);
        } catch (Exception e) {
            return null;
        }
    }
}