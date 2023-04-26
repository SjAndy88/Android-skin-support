package skin.support.app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;

import skin.support.widget.SkinCompatCardView;



public class SkinCardViewInflater implements SkinLayoutInflater {
    @Override
    public View createView(@NonNull Context context, final String name, @NonNull AttributeSet attrs) {
        View view = null;
        switch (name) {
            case "androidx.cardview.widget.CardView":
                view = new SkinCompatCardView(context, attrs);
                break;
            default:
                break;
        }
        return view;
    }
}
