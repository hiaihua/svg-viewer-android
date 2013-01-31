package biz.codefuture.svgviewer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class DocumentPropertiesDialog extends DialogFragment {
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Document properties here")
               .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // maybe put a save button here to update doc props 
                	   // TODO is there a reason to push saved properties somewhere on the web?
                   }
               });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
