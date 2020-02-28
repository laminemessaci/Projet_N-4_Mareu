package com.lamine.mareu.view;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.lamine.mareu.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Lamine MESSACI on 28/02/2020.
 */
public class ItemMeeting extends RecyclerView.ViewHolder {

   public final ImageView mImageView;
   public final TextView mDescriptionTextView;
   public final TextView mParticipantsTextView;
   public final ImageButton mDeleteImageButton;

    public ItemMeeting (@NonNull View itemView) {
        super (itemView);

        //initialisation
        mImageView = itemView.findViewById (R.id.circle_item);
        mDescriptionTextView = itemView.findViewById (R.id.description_item);
        mParticipantsTextView = itemView.findViewById (R.id.participants_item);
        mDeleteImageButton    = itemView.findViewById (R.id.delete_item);

    }
}
