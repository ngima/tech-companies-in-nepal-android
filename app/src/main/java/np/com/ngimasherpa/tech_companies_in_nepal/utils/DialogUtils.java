package np.com.ngimasherpa.tech_companies_in_nepal.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import np.com.ngimasherpa.tech_companies_in_nepal.R;
import np.com.ngimasherpa.tech_companies_in_nepal.model.Company;
import np.com.ngimasherpa.tech_companies_in_nepal.preview.GetFavicon;

/**
 * Created by ngima on 9/19/17.
 */

public class DialogUtils {

    public static void showError(final Context context, String errorMessage) {

        final NiftyDialogBuilder niftyDialogBuilder = NiftyDialogBuilder.getInstance(context)
                .withTitle("Error")                                  //.withTitle(null)  no title
                .withTitleColor(ContextCompat.getColor(context, android.R.color.white))                                  //def
                .withMessage(errorMessage)                     //.withMessage(null)  no Msg
                .withMessageColor(ContextCompat.getColor(context, android.R.color.white))                              //def  | withMessageColor(int resid)
                .withDialogColor(ContextCompat.getColor(context, R.color.dialog_error_bg))                               //def  | withDialogColor(int resid)
                .withIcon(context.getResources().getDrawable(R.drawable.ic_error))
                .withDuration(700)                                          //def
                .withEffect(Effectstype.Shake)                                         //def Effectstype.Slidetop
                .withButton1Text("OK")                                      //def gone
//                .withButton2Text()                                  //def gone
                .isCancelableOnTouchOutside(true)                           //def    | isCancelable(true)
//                .setCustomView(R.layout.custom_view, context)         //.setCustomView(View or ResId,context)
                ;

        niftyDialogBuilder.setButton1Click(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                niftyDialogBuilder.hide();
            }
        });

        niftyDialogBuilder.show();
    }

    public static void viewDetails(final Context context, final Company company) {

        final NiftyDialogBuilder niftyDialogBuilder = NiftyDialogBuilder.getInstance(context)
                .withTitle(company.name)
                .withTitleColor(ContextCompat.getColor(context, android.R.color.white))                                  //def
                .withMessage(company.description + "\n\nLocation:" + company.location)                     //.withMessage(null)  no Msg
                .withMessageColor(ContextCompat.getColor(context, android.R.color.white))                              //def  | withMessageColor(int resid)
                .withDialogColor(ContextCompat.getColor(context, R.color.dialog_details_bg))                               //def  | withDialogColor(int resid)
                .withDuration(700)
                .withEffect(Effectstype.Slidetop)
                .withButton1Text("Close")
                .withButton2Text("Visit website")
                .isCancelableOnTouchOutside(true);
        niftyDialogBuilder.setButton1Click(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                niftyDialogBuilder.hide();
            }
        });

        niftyDialogBuilder.setButton2Click(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.loadWebUrlIn(context, company.website);
                niftyDialogBuilder.hide();
            }
        });

        niftyDialogBuilder.show();
    }
}
