package com.ejupialked.todoapp.view.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.ejupialked.todoapp.R;

import butterknife.ButterKnife;

/**
 * <p>
 * BaseActivity contains some modifications to the native AppCompatActivity.
 * Mainly, it use ButterKnife for view binding and it automatically check if
 * toolbar exists.
 * </p>
 */
public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        bindViews();
        initView();
    }

    /**
     * Use this method to initialize view components. This method is called after {@link
     * BaseActivity#bindViews()}
     */
    public void initView() {
    }



    /**
     * Every object annotated with {@link butterknife} its gonna injected trough butterknife
     */
    private void bindViews() {
        ButterKnife.bind(this);
    }

    /**
     * @return The layout id that's gonna be the activity view.
     */
    protected abstract int getLayoutId();
}
