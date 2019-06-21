package com.oldmen.superapp.ui.activity.addchannel;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.oldmen.superapp.R;

import java.util.Arrays;

public class SettingsActivity extends AppCompatActivity {

    private boolean[] mAnyoneCanSelectedItems, mMemberCanSelectedItems, mModsCanSelectedItems, mAdminCanSelectedItems;
    private TextView anyoneCan, membersCan, modsCan, adminCan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        anyoneCan = findViewById(R.id.anyone_can);
        membersCan = findViewById(R.id.members_can);
        modsCan = findViewById(R.id.mods_can);
        adminCan = findViewById(R.id.admin_can);

        mAnyoneCanSelectedItems = new boolean[4];
        Arrays.fill(mAnyoneCanSelectedItems, true);

        mMemberCanSelectedItems = new boolean[4];
        Arrays.fill(mMemberCanSelectedItems, true);

        mModsCanSelectedItems = new boolean[7];
        Arrays.fill(mModsCanSelectedItems, true);

        mAdminCanSelectedItems = new boolean[10];
        Arrays.fill(mAdminCanSelectedItems, true);

        anyoneCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAnyonePermissionDialog();
            }
        });

        membersCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMembersPermissionDialog();
            }
        });

        modsCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openModsPermissionDialog();
            }
        });

        adminCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAdminPermissionDialog();
            }
        });
    }

    private void openAnyonePermissionDialog() {
        boolean[] checkedBoxes = new boolean[4];

        int count = 0;
        for (boolean e : mAnyoneCanSelectedItems) {
            checkedBoxes[count++] = e;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Permissions")
                .setMultiChoiceItems(R.array.basic_permissions, checkedBoxes,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                if (isChecked) {
                                    mAnyoneCanSelectedItems[which] = true;
                                } else if (mAnyoneCanSelectedItems[which]) {
                                    mAnyoneCanSelectedItems[which] = false;
                                }
                            }
                        })
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK, so save the selectedItems results somewhere
                        // or return them to the component that opened the dialog
                        for (boolean e : mAnyoneCanSelectedItems) {
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

    private void openMembersPermissionDialog() {
        boolean[] checkedBoxes = new boolean[4];

        int count = 0;
        for (boolean e : mMemberCanSelectedItems) {
            checkedBoxes[count++] = e;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Permissions")
                .setMultiChoiceItems(R.array.basic_permissions, checkedBoxes,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                if (isChecked) {
                                    mMemberCanSelectedItems[which] = true;
                                } else if (mMemberCanSelectedItems[which]) {
                                    mMemberCanSelectedItems[which] = false;
                                }
                            }
                        })
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK, so save the selectedItems results somewhere
                        // or return them to the component that opened the dialog
                        for (boolean e : mMemberCanSelectedItems) {
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

    private void openModsPermissionDialog() {
        boolean[] checkedBoxes = new boolean[7];

        int count = 0;
        for (boolean e : mModsCanSelectedItems) {
            checkedBoxes[count++] = e;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Permissions")
                .setMultiChoiceItems(R.array.mods_permissions, checkedBoxes,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                if (isChecked) {
                                    mModsCanSelectedItems[which] = true;
                                } else if (mModsCanSelectedItems[which]) {
                                    mModsCanSelectedItems[which] = false;
                                }
                            }
                        })
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK, so save the selectedItems results somewhere
                        // or return them to the component that opened the dialog
                        for (boolean e : mModsCanSelectedItems) {
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

    private void openAdminPermissionDialog() {
        boolean[] checkedBoxes = new boolean[10];

        int count = 0;
        for (boolean e : mAdminCanSelectedItems) {
            checkedBoxes[count++] = e;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Permissions")
                .setMultiChoiceItems(R.array.admin_permissions, checkedBoxes,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                if (isChecked) {
                                    mAdminCanSelectedItems[which] = true;
                                } else if (mAdminCanSelectedItems[which]) {
                                    mAdminCanSelectedItems[which] = false;
                                }
                            }
                        })
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK, so save the selectedItems results somewhere
                        // or return them to the component that opened the dialog
                        for (boolean e : mAdminCanSelectedItems) {
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
