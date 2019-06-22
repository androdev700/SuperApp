package com.oldmen.superapp.ui.activity.addChannel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.oldmen.superapp.R;

import java.util.Arrays;

public class AdvancedSettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean[] mContentTypeItems, mCategoriesAllowedItems, mServiceReqItems;
    private TextView mContentType, mCategoriesAllowed, mConnectedApp, mServiceReq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_settings);

        mContentType = findViewById(R.id.content_type);
        mContentType.setOnClickListener(this);
        mCategoriesAllowed = findViewById(R.id.categories_allowed);
        mCategoriesAllowed.setOnClickListener(this);
        mConnectedApp = findViewById(R.id.connected_app);
        mServiceReq = findViewById(R.id.service_required);
        mServiceReq.setOnClickListener(this);

        mContentTypeItems = new boolean[4];
        Arrays.fill(mContentTypeItems, true);

        mCategoriesAllowedItems = new boolean[3];
        Arrays.fill(mCategoriesAllowedItems, true);

        mServiceReqItems = new boolean[5];
        Arrays.fill(mServiceReqItems, true);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.content_type:
                contentTypePermissionDialog();
                break;
            case R.id.categories_allowed:
                categoiesAllowedPermissionDialog();
                break;
            case R.id.service_required:
                serviceRequiredDialog();
                break;
        }
    }

    private void contentTypePermissionDialog() {
        boolean[] checkedBoxes = new boolean[4];

        int count = 0;
        for (boolean e : mContentTypeItems) {
            checkedBoxes[count++] = e;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Content Type")
                .setMultiChoiceItems(R.array.content_type, checkedBoxes,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                if (isChecked) {
                                    mContentTypeItems[which] = true;
                                } else if (mContentTypeItems[which]) {
                                    mContentTypeItems[which] = false;
                                }
                            }
                        })
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK, so save the selectedItems results somewhere
                        // or return them to the component that opened the dialog
                        for (boolean e : mContentTypeItems) {
                            Log.e("TAG", Boolean.toString(e));
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void categoiesAllowedPermissionDialog() {
        boolean[] checkedBoxes = new boolean[3];

        int count = 0;
        for (boolean e : mCategoriesAllowedItems) {
            checkedBoxes[count++] = e;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Categories Allowed")
                .setMultiChoiceItems(R.array.categories_allowed, checkedBoxes,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                if (isChecked) {
                                    mCategoriesAllowedItems[which] = true;
                                } else if (mCategoriesAllowedItems[which]) {
                                    mCategoriesAllowedItems[which] = false;
                                }
                            }
                        })
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK, so save the selectedItems results somewhere
                        // or return them to the component that opened the dialog
                        for (boolean e : mCategoriesAllowedItems) {
                            Log.e("TAG", Boolean.toString(e));
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void serviceRequiredDialog() {
        boolean[] checkedBoxes = new boolean[5];

        int count = 0;
        for (boolean e : mServiceReqItems) {
            checkedBoxes[count++] = e;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Services Required")
                .setMultiChoiceItems(R.array.service_req, checkedBoxes,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                if (isChecked) {
                                    mServiceReqItems[which] = true;
                                } else if (mServiceReqItems[which]) {
                                    mServiceReqItems[which] = false;
                                }
                            }
                        })
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK, so save the selectedItems results somewhere
                        // or return them to the component that opened the dialog
                        for (boolean e : mCategoriesAllowedItems) {
                            Log.e("TAG", Boolean.toString(e));
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
