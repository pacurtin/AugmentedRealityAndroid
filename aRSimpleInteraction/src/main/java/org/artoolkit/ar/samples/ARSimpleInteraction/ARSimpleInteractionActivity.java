package org.artoolkit.ar.samples.ARSimpleInteraction;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.artoolkit.ar.base.ARActivity;
import org.artoolkit.ar.base.rendering.ARRenderer;

public class ARSimpleInteractionActivity extends ARActivity implements OnTouchListener {
    /**
     * The FrameLayout where the AR view is displayed.
     */
    private FrameLayout mainLayout;
    private Button rightButton;
    private Button leftButton;
    private Button aButton;
    private Button bButton;
    private TextView speedometer;

    boolean aButtonHeldDown = false;

    Context context =this;

    private OpenGLRenderer renderer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mainLayout = (FrameLayout) this.findViewById(R.id.mainLayout);
        rightButton = (Button) this.findViewById(R.id.rightButton);
        leftButton = (Button) this.findViewById(R.id.leftButton);
        aButton = (Button) this.findViewById(R.id.aButton);
        bButton = (Button) this.findViewById(R.id.bButton);
        speedometer = (TextView) this.findViewById(R.id.speedometer);

        rightButton.setOnTouchListener(this);
        leftButton.setOnTouchListener(this);
        aButton.setOnTouchListener(this);
        bButton.setOnTouchListener(this);

        renderer= new OpenGLRenderer(context);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch(v.getId()){
            case R.id.rightButton:
                renderer.turnCarRight();
                break;
            case R.id.leftButton:
                renderer.turnCarLeft();
                break;
            case R.id.aButton:
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    aButtonHeldDown = true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    aButtonHeldDown = false;
                }

                if(aButtonHeldDown == true){
                    renderer.aButtonPressed();
                }
                if(aButtonHeldDown != true){
                    renderer.aButtonReleased();
                }
                break;
            case R.id.bButton:
                renderer.bButtonPressed();
                break;
        }
        return true;
    }

    @Override
    protected ARRenderer supplyRenderer() {
        return renderer;
    }


    @Override
    protected FrameLayout supplyFrameLayout() {
        return mainLayout;
    }
}