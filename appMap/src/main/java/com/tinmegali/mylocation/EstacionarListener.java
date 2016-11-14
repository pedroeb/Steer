package com.tinmegali.mylocation;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

/**
 * Created by Pedro on 22/09/2016.
 */

public class EstacionarListener implements View.OnClickListener {

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(),"Usted ha estacionado",Toast.LENGTH_LONG).show();
    }


}