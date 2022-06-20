package bg.tu.varna.sit.fn07062021;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import bg.tu.varna.sit.fn07062021.adapters.MovieAdapter;
import bg.tu.varna.sit.fn07062021.data.MovieSource;
import bg.tu.varna.sit.fn07062021.listeners.OnModifiedMovieListener;
import bg.tu.varna.sit.fn07062021.listeners.OnClickMovieListener;
import bg.tu.varna.sit.fn07062021.models.Movie;
import bg.tu.varna.sit.fn07062021.tasks.DeleteMovieTask;
import bg.tu.varna.sit.fn07062021.tasks.ViewTask;

public class MainActivity extends BaseActivity
        implements OnClickMovieListener, OnModifiedMovieListener {

    private RecyclerView recyclerView;
    private FloatingActionButton add;
    private HandlerThread thread;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = findViewById(R.id.floatingActionButton);
        add.setOnClickListener(this);

        recyclerView = findViewById(R.id.recyclerView);
        MovieAdapter movieAdapter = new MovieAdapter(this, MovieSource.generateMovies(15));
        recyclerView.setAdapter(movieAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        executeThreads(movieAdapter);
    }

    private void executeThreads(MovieAdapter movieAdapter) {
        thread = new HandlerThread("Update Visits");
        thread.start();
        handler = new Handler(thread.getLooper());
        handler.postDelayed(new ViewTask(handler, MainActivity.this, movieAdapter.getItems()), 1000);

        thread = new HandlerThread("Delete Visits");
        thread.start();
        handler = new Handler(thread.getLooper());
        handler.post(new DeleteMovieTask(handler, MainActivity.this, movieAdapter.getItems()));
    }

    @Override
    public void onClick(View view) {
        AddDialogFragment fragment = AddDialogFragment.newInstance();
        fragment.show(getSupportFragmentManager(), "add_fragment_dialog");
    }

    @Override
    public void onClickDisplayMovie(Movie movie) {
        DisplayFragment fragment = DisplayFragment.newInstance(movie);
        fragment.show(getSupportFragmentManager(), "display_fragment_dialog");
    }

    @Override
    public void onClickEditMovie(Movie movie, int position) {
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra(ARG_MOVIE, movie);
        startActivityForResult(intent, RETURN_CODE_EDIT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RETURN_CODE_EDIT) {
            if(data != null) {
                Movie movie = data.getParcelableExtra(ARG_MOVIE);
                if(recyclerView != null) {
                    MovieAdapter adapter = (MovieAdapter) recyclerView.getAdapter();
                    if(adapter != null) {
                        adapter.updateMovie(movie);
                    }
                }
            }
        }
    }

    @Override
    public void onAddMovie(Movie movie) {
        if(recyclerView != null) {
            MovieAdapter adapter = (MovieAdapter) recyclerView.getAdapter();
            if(adapter != null) {
                adapter.addMovie(movie);
            }
        }
    }

    @Override
    public void onDeleteMovie(Movie movie) {
        if(recyclerView != null) {
            MovieAdapter adapter = (MovieAdapter) recyclerView.getAdapter();
            if(adapter != null) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.delete(movie);
                    }
                });
            }
        }
    }

    @Override
    public void onUpdateMovie(Movie movie) {
        if(recyclerView != null) {
            MovieAdapter adapter = (MovieAdapter) recyclerView.getAdapter();
            if(adapter != null) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.updateMovie(movie);
                    }
                });
            }
        }
    }
}