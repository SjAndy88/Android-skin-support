package skin.support.design.widget;

import static skin.support.widget.SkinCompatHelper.INVALID_ID;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.annotation.StyleRes;

import com.google.android.material.textfield.TextInputLayout;

import java.lang.reflect.Method;

import skin.support.content.res.SkinCompatResources;
import skin.support.design.R;
import skin.support.widget.SkinCompatBackgroundHelper;
import skin.support.widget.SkinCompatHelper;
import skin.support.widget.SkinCompatSupportable;



public class SkinMaterialTextInputLayout extends TextInputLayout implements SkinCompatSupportable {

    private final SkinCompatBackgroundHelper mBackgroundTintHelper;

    private int mCounterTextColorResId = INVALID_ID;
    private int mErrorTextColorResId = INVALID_ID;
    private int mDefaultHintFocusedTextColorResId = INVALID_ID;

    public SkinMaterialTextInputLayout(Context context) {
        this(context, null);
    }

    public SkinMaterialTextInputLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkinMaterialTextInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBackgroundTintHelper = new SkinCompatBackgroundHelper(this);
        mBackgroundTintHelper.loadFromAttributes(attrs, defStyleAttr);

        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TextInputLayout, defStyleAttr, R.style.Widget_Design_TextInputLayout);
        if (a.hasValue(R.styleable.TextInputLayout_android_textColorHint)) {
            mDefaultHintFocusedTextColorResId =
                    a.getResourceId(R.styleable.TextInputLayout_android_textColorHint, INVALID_ID);
            applyFocusedTextColorResource();
        }

        int errorTextAppearance = a.getResourceId(R.styleable.TextInputLayout_errorTextAppearance, INVALID_ID);
        loadErrorTextColorResFromAttributes(errorTextAppearance);
        int counterTextAppearance = a.getResourceId(R.styleable.TextInputLayout_counterTextAppearance, INVALID_ID);
        loadCounterTextColorResFromAttributes(counterTextAppearance);
        a.recycle();
    }

    private void loadCounterTextColorResFromAttributes(@StyleRes int resId) {
        if (resId != INVALID_ID) {
            TypedArray counterTA = getContext().obtainStyledAttributes(resId, skin.support.R.styleable.SkinTextAppearance);
            if (counterTA.hasValue(skin.support.R.styleable.SkinTextAppearance_android_textColor)) {
                mCounterTextColorResId = counterTA.getResourceId(skin.support.R.styleable.SkinTextAppearance_android_textColor, INVALID_ID);
            }
            counterTA.recycle();
        }
        applyCounterTextColorResource();
    }

    @Override
    public void setCounterEnabled(boolean enabled) {
        super.setCounterEnabled(enabled);
        if (enabled) {
            applyCounterTextColorResource();
        }
    }

    private void applyCounterTextColorResource() {
        mCounterTextColorResId = SkinCompatHelper.checkResourceId(mCounterTextColorResId);
        if (mCounterTextColorResId != INVALID_ID) {
            setCounterTextColor(SkinCompatResources.getColorStateList(getContext(), mCounterTextColorResId));
            updateEditTextBackgroundInternal();
        }
    }

    @Override
    public void setErrorTextAppearance(@StyleRes int resId) {
        super.setErrorTextAppearance(resId);
        loadErrorTextColorResFromAttributes(resId);
    }

    private void loadErrorTextColorResFromAttributes(@StyleRes int resId) {
        if (resId != INVALID_ID) {
            TypedArray errorTA = getContext().obtainStyledAttributes(resId, skin.support.R.styleable.SkinTextAppearance);
            if (errorTA.hasValue(skin.support.R.styleable.SkinTextAppearance_android_textColor)) {
                mErrorTextColorResId = errorTA.getResourceId(skin.support.R.styleable.SkinTextAppearance_android_textColor, INVALID_ID);
            }
            errorTA.recycle();
        }
        applyErrorTextColorResource();
    }

    @Override
    public void setErrorEnabled(boolean enabled) {
        super.setErrorEnabled(enabled);
        if (enabled) {
            applyErrorTextColorResource();
        }
    }

    @SuppressLint("PrivateResource")
    private void applyErrorTextColorResource() {
        mErrorTextColorResId = SkinCompatHelper.checkResourceId(mErrorTextColorResId);
        if (mErrorTextColorResId != INVALID_ID
                && mErrorTextColorResId != R.color.design_error) {
            setErrorTextColor(SkinCompatResources.getColorStateList(getContext(), mErrorTextColorResId));
            updateEditTextBackgroundInternal();
        }
    }

    private void updateEditTextBackgroundInternal() {
        try {
            Method updateEditTextBackground = TextInputLayout.class.getDeclaredMethod("updateEditTextBackground");
            updateEditTextBackground.setAccessible(true);
            updateEditTextBackground.invoke(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void applyFocusedTextColorResource() {
        mDefaultHintFocusedTextColorResId = SkinCompatHelper.checkResourceId(mDefaultHintFocusedTextColorResId);
        if (mDefaultHintFocusedTextColorResId != INVALID_ID
                && mDefaultHintFocusedTextColorResId != R.color.abc_hint_foreground_material_light) {
            setDefaultHintTextColor(SkinCompatResources.getColorStateList(getContext(), mDefaultHintFocusedTextColorResId));
        }
    }

    @Override
    public void applySkin() {
        applyErrorTextColorResource();
        applyCounterTextColorResource();
        applyFocusedTextColorResource();
        if (mBackgroundTintHelper != null) {
            mBackgroundTintHelper.applySkin();
        }
    }

}
