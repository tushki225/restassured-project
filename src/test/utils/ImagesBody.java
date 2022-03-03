package test.utils;

import java.util.List;

public class ImagesBody {

    private ImagesBody() {
    }

    public static synchronized String getImagesUrlsBody(List<String> imagesUrls) {
        ViStringBuilder body = new ViStringBuilder();
        body.append("{");
        body.append("\"images\": [");
        for (int i = 0; i < imagesUrls.size() - 1; i++) {
            body.append("\"" + imagesUrls.get(i) + "\",");
        }
        body.append("\"" + imagesUrls.get(imagesUrls.size() - 1) + "\"");
        body.append("]");
        body.append("}");
        return body.getStringValue();
    }

}
