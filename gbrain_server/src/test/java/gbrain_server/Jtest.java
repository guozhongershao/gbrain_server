package gbrain_server;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import com.musearcher.gbrain.common.Constants;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Jtest {
	
    @Test
    public void run() throws Exception {
        String IMGUR_CLIENT_ID = "...";
        MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("title", "Square Logo")
                .addFormDataPart("image", "index.jpg",
                        RequestBody.create(MEDIA_TYPE_PNG, new File("E:\\AndroidStudioProjects\\g_brain\\app\\src\\test\\java\\com\\musearcher\\g_brain\\index.jpg")))
                .build();
        Request request = new Request.Builder()
                .header("Authorization", "Client-ID " + IMGUR_CLIENT_ID)
                .url(Constants.CONNECT_UPLOAD)
                .addHeader("dealer", "ImageUnploadDealer")
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            System.out.println(response.body().string());
        }
    }
}
