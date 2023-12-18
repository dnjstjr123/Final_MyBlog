package com.example.myblog;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class MainActivity extends AppCompatActivity {
    private TextView myTitle;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = findViewById(R.id.linearLayout);

        // Retrofit 설정
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000") // Django API의 base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetAPI getAPI = retrofit.create(GetAPI.class);

        // GET 요청 실행
        Call<List<Post>> call = getAPI.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> posts = response.body();

                for (int i = 0; i < posts.size(); i++) {
                    Post post = posts.get(i);
                    String title = post.getTitle();
                    String text = post.getText();
                    String date = post.getPublished_date();
                    String imageUrl = post.getImage();
                    String imageU = imageUrl.replace("10.0.2.2", "127.0.0.1");
                    File file = new File(imageU);
                    Log.d("Tag", imageU);

                    // 포스트 제목을 TextView로 추가
                    TextView titleTextView = new TextView(MainActivity.this);
                    titleTextView.setText("\n"+"제목- " + title + " ( " +date+" )  "+"\n"+"내용 \n"+text+"\n" );
                    titleTextView.setTextSize(15);
                    linearLayout.addView(titleTextView);

                    // 포스트 이미지를 ImageView로 추가
                    ImageView imageView = new ImageView(MainActivity.this);
                    imageView.setLayoutParams(new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
                    ));
                    Picasso.get().load(imageUrl).resize(900, 500).into(imageView);
                    linearLayout.addView(imageView);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                myTitle.setText(t.getMessage());
            }
        });
    }
}