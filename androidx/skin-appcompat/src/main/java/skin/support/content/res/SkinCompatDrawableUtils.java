package skin.support.content.res;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.ScaleDrawable;

import androidx.annotation.NonNull;

import skin.support.utils.SkinCompatVersionUtils;

class SkinCompatDrawableUtils {

    private static final String VECTOR_DRAWABLE_CLAZZ_NAME
            = "android.graphics.drawable.VectorDrawable";

    /**
     * Attempt the fix any issues in the given drawable, usually caused by platform bugs in the
     * implementation. This method should be call after retrieval from
     * {@link android.content.res.Resources} or a {@link android.content.res.TypedArray}.
     */
    static void fixDrawable(@NonNull final Drawable drawable) {
    }

    /**
     * Some drawable implementations have problems with mutation. This method returns false if
     * there is a known issue in the given drawable's implementation.
     */
    public static boolean canSafelyMutateDrawable(@NonNull Drawable drawable) {
        if (drawable instanceof DrawableContainer) {
            // If we have a DrawableContainer, let's traverse it's child array
            final Drawable.ConstantState state = drawable.getConstantState();
            if (state instanceof DrawableContainer.DrawableContainerState) {
                final DrawableContainer.DrawableContainerState containerState =
                        (DrawableContainer.DrawableContainerState) state;
                for (final Drawable child : containerState.getChildren()) {
                    if (!canSafelyMutateDrawable(child)) {
                        return false;
                    }
                }
            }
        } else if (SkinCompatVersionUtils.isV4DrawableWrapper(drawable)) {
            return canSafelyMutateDrawable(SkinCompatVersionUtils.getV4DrawableWrapperWrappedDrawable(drawable));
        } else if (SkinCompatVersionUtils.isV4WrappedDrawable(drawable)) {
            return canSafelyMutateDrawable(SkinCompatVersionUtils.getV4WrappedDrawableWrappedDrawable(drawable));
        } else if (SkinCompatVersionUtils.isV7DrawableWrapper(drawable)) {
            return canSafelyMutateDrawable(SkinCompatVersionUtils.getV7DrawableWrapperWrappedDrawable(drawable));
        } else if (drawable instanceof ScaleDrawable) {
            Drawable scaleDrawable = ((ScaleDrawable) drawable).getDrawable();
            if (scaleDrawable != null) {
                return canSafelyMutateDrawable(scaleDrawable);
            }
        }

        return true;
    }

    /**
     * VectorDrawable has an issue on API 21 where it sometimes doesn't create its tint filter.
     * Fixed by toggling it's state to force a filter creation.
     */
    private static void fixVectorDrawableTinting(final Drawable drawable) {
        final int[] originalState = drawable.getState();
        if (originalState == null || originalState.length == 0) {
            // The drawable doesn't have a state, so set it to be checked
            drawable.setState(SkinCompatThemeUtils.CHECKED_STATE_SET);
        } else {
            // Else the drawable does have a state, so clear it
            drawable.setState(SkinCompatThemeUtils.EMPTY_STATE_SET);
        }
        // Now set the original state
        drawable.setState(originalState);
    }
}
