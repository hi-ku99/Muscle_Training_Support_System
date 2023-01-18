package com.example.mtss;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.fragment.app.DialogFragment;

public class ExDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        int ob_parts=new ob_zintai().getParts_obj();
        int re_parts=new re_zintai().getParts_obj();
        final int ex_right_arm=new ex_right_arm().getParts();
        int ex_left_arm=new ex_left_arm().getParts();
        int ex_re_right_arm=new ex_re_right_arm().getParts();
        int ex_re_left_arm=new ex_re_left_arm().getParts();
        int ex_stomach=new ex_stomach().getParts();

        switch(ob_parts) {
            case R.id.ob_right_Arm:
                builder.setMessage(R.string.dialog_arm_message)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ob_zintai().reset();
                                startActivity(new Intent(getContext(), ex_right_arm.class));

                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                break;

            case R.id.ob_left_Arm:
                builder.setMessage(R.string.dialog_arm_message)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ob_zintai().reset();
                                startActivity(new Intent(getContext(), ex_left_arm.class));
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ob_zintai().reset();
                            }
                        });
                break;

            case R.id.Stomach:
                builder.setMessage(R.string.dialog_stomach_message)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ob_zintai().reset();
                                startActivity(new Intent(getContext(), ex_stomach.class));
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ob_zintai().reset();
                            }
                        });
                break;

            case R.id.Chest:
                builder.setMessage(R.string.dialog_chest_message)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ob_zintai().reset();
                                startActivity(new Intent(getContext(), chest_first.class));
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ob_zintai().reset();
                            }
                        });
                break;

            case R.id.ob_right_Leg:
                builder.setMessage(R.string.dialog_leg_message)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ob_zintai().reset();
                                startActivity(new Intent(getContext(), kyakubu_first.class));
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ob_zintai().reset();
                            }
                        });
                break;

            case R.id.ob_left_Leg:
                builder.setMessage(R.string.dialog_leg_message)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ob_zintai().reset();
                                startActivity(new Intent(getContext(), kyakubu_first.class));
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ob_zintai().reset();
                            }
                        });
                break;

            default:
                break;
        }

        switch (re_parts) {
            case R.id.re_right_Arm:
                builder.setMessage(R.string.dialog_arm_message)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new re_zintai().reset();
                                startActivity(new Intent(getContext(), ex_re_right_arm.class));
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new re_zintai().reset();
                            }
                        });
                break;

            case R.id.re_left_Arm:
                builder.setMessage(R.string.dialog_arm_message)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new re_zintai().reset();
                                startActivity(new Intent(getContext(), ex_re_left_arm.class));
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new re_zintai().reset();
                            }
                        });
                break;

            case R.id.re_right_Leg:
                builder.setMessage(R.string.dialog_leg_message)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new re_zintai().reset();
                                startActivity(new Intent(getContext(), kyakubu_first.class));
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new re_zintai().reset();
                            }
                        });
                break;

            case R.id.re_left_Leg:
                builder.setMessage(R.string.dialog_leg_message)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new re_zintai().reset();
                                startActivity(new Intent(getContext(), kyakubu_first.class));
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new re_zintai().reset();
                            }
                        });
                break;

            case R.id.Back:
                builder.setMessage(R.string.dialog_back_message)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new re_zintai().reset();
                                startActivity(new Intent(getContext(), haikin_first.class));
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new re_zintai().reset();
                            }
                        });
                break;

            case R.id.Ass:
                builder.setMessage(R.string.dialog_ass_message)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new re_zintai().reset();
                                startActivity(new Intent(getContext(), denbu_first.class));
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new re_zintai().reset();
                            }
                        });
                break;

            default:
                break;

        }

        switch (ex_left_arm){
            case R.id.Zyou2:
                builder.setMessage(R.string.dialog_arm2_message)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ex_left_arm().reset();
                                startActivity(new Intent(getContext(), nitoukin_first.class));
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ex_left_arm().reset();
                            }
                        });
                break;

            case R.id.Zyou3:
                builder.setMessage(R.string.dialog_arm3_message)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ex_left_arm().reset();
                                startActivity(new Intent(getContext(), santoukin_first.class));
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ex_left_arm().reset();
                            }
                        });
                break;

            case R.id.Shoulder:
                builder.setMessage(R.string.dialog_shoulder_message)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ex_left_arm().reset();
                                startActivity(new Intent(getContext(), shoulder_first.class));
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ex_left_arm().reset();
                            }
                        });
                break;

            case R.id.front_Arm:
                builder.setMessage(R.string.dialog_front_arm_message)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ex_left_arm().reset();
                                startActivity(new Intent(getContext(), forearm_first.class));
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ex_left_arm().reset();
                            }
                        });
                break;
            default:
                break;
        }

        switch (ex_right_arm){
            case R.id.Zyou2:
                builder.setMessage(R.string.dialog_arm2_message)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ex_right_arm().reset();
                                startActivity(new Intent(getContext(), nitoukin_first.class));
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ex_right_arm().reset();
                            }
                        });
                break;

            case R.id.Zyou3:
                builder.setMessage(R.string.dialog_arm3_message)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ex_right_arm().reset();
                                startActivity(new Intent(getContext(), santoukin_first.class));
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ex_right_arm().reset();
                            }
                        });
                break;

            case R.id.Shoulder:
                builder.setMessage(R.string.dialog_shoulder_message)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ex_right_arm().reset();
                                startActivity(new Intent(getContext(), shoulder_first.class));
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ex_right_arm().reset();
                            }
                        });
                break;

            case R.id.front_Arm:
                builder.setMessage(R.string.dialog_front_arm_message)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ex_right_arm().reset();
                                startActivity(new Intent(getContext(), forearm_first.class));
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ex_right_arm().reset();
                            }
                        });
                break;
            default:
                break;
        }

        switch (ex_re_left_arm){
            case R.id.Zyou2:
                builder.setMessage(R.string.dialog_arm2_message)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ex_re_left_arm().reset();
                                startActivity(new Intent(getContext(), nitoukin_first.class));
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ex_re_left_arm().reset();
                            }
                        });
                break;

            case R.id.Zyou3:
                builder.setMessage(R.string.dialog_arm3_message)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ex_re_left_arm().reset();
                                startActivity(new Intent(getContext(), santoukin_first.class));
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ex_re_left_arm().reset();
                            }
                        });
                break;

            case R.id.Shoulder:
                builder.setMessage(R.string.dialog_shoulder_message)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ex_re_left_arm().reset();
                                startActivity(new Intent(getContext(), shoulder_first.class));
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ex_re_left_arm().reset();
                            }
                        });
                break;

            case R.id.front_Arm:
                builder.setMessage(R.string.dialog_front_arm_message)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ex_re_left_arm().reset();
                                startActivity(new Intent(getContext(), forearm_first.class));
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ex_re_left_arm().reset();
                            }
                        });
                break;
            default:
                break;
        }

        switch (ex_re_right_arm){
            case R.id.Zyou2:
                builder.setMessage(R.string.dialog_arm2_message)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ex_re_right_arm().reset();
                                startActivity(new Intent(getContext(), nitoukin_first.class));
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ex_re_right_arm().reset();
                            }
                        });
                break;

            case R.id.Zyou3:
                builder.setMessage(R.string.dialog_arm3_message)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ex_re_right_arm().reset();
                                startActivity(new Intent(getContext(), santoukin_first.class));
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ex_re_right_arm().reset();
                            }
                        });
                break;

            case R.id.Shoulder:
                builder.setMessage(R.string.dialog_shoulder_message)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ex_re_right_arm().reset();
                                startActivity(new Intent(getContext(), shoulder_first.class));
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ex_re_right_arm().reset();
                            }
                        });
                break;

            case R.id.front_Arm:
                builder.setMessage(R.string.dialog_front_arm_message)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ex_re_right_arm().reset();
                                startActivity(new Intent(getContext(), forearm_first.class));
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ex_re_right_arm().reset();
                            }
                        });
                break;
            default:
                break;
        }

        switch (ex_stomach){
            case R.id.Fukutyoku:
                builder.setMessage(R.string.dialog_fukutyoku_message)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ex_stomach().reset();
                                startActivity(new Intent(getContext(), hukutyoku_first.class));
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ex_stomach().reset();
                            }
                        });
                break;

            case R.id.Fukusya:
                builder.setMessage(R.string.dialog_fukusya_message)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ex_stomach().reset();
                                startActivity(new Intent(getContext(), hukusya_first.class));
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ex_stomach().reset();
                            }
                        });
                break;


            default:
                break;
        }



    // Create the AlertDialog object and return it
    return builder.create();
}
}
