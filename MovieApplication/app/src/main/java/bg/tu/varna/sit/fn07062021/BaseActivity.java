package bg.tu.varna.sit.fn07062021;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    protected static final String ARG_MOVIE = "movie";
    protected static final int RETURN_CODE_EDIT = 1;
}
