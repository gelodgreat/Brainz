package com.shamwow.brainz;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class frm_comp_essential extends AppCompatActivity {

    databasecontroller myDB;
    private CompEssentialsQuestionLibrary celib = new CompEssentialsQuestionLibrary();

    @BindView(R.id.ce_rb_a)RadioButton rb_a;
    @BindView(R.id.ce_rb_b) RadioButton rb_b;
    @BindView(R.id.ce_rb_c) RadioButton rb_c;
    @BindView(R.id.ce_tv_question)TextView tv_question;
    @BindView(R.id.ce_btn_submit)Button btn_next;
    @BindView(R.id.ce_tv_score) TextView tv_score;

    private String answer;
    private int score =0;
    private int number =0;
    String ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_comp_essential);

        myDB = new databasecontroller(this);
        ButterKnife.bind(this);
        set_questions();
        conditions();

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoring();
            }
        });
    }

    public void scoring(){
        if (number==0){
            score=0;

        } else {
            if (number != 6){
                if (ref==answer){
                    score=score+1;
                    updateScore(score);
                    set_questions();


                }else if (ref==answer){
                    score=score+1;
                    updateScore(score);
                    set_questions();

                }else if (ref==answer){
                    score=score+1;
                    updateScore(score);
                    set_questions();

                } else {
                    updateScore(score);
                    set_questions();

                }
            } else {


                tv_question.setVisibility(View.INVISIBLE);
                rb_a.setVisibility(View.GONE);
                rb_b.setVisibility(View.GONE);
                rb_c.setVisibility(View.GONE);
                Toast.makeText(frm_comp_essential.this,"Finish", Toast.LENGTH_LONG).show();
                updateScore(score);
                showMessage("Brainz Inc.","Score: "+score);

                btn_next.setText("Finish");
                btn_next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();

                    }
                });
            }


        }
    }

    public void conditions(){

        rb_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref = rb_a.getText().toString();
            }
        });

        rb_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref = rb_b.getText().toString();
            }
        });

        rb_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref = rb_c.getText().toString();
            }
        });

    }


    public void set_questions(){

        tv_question.setText(celib.CompEssentialsgetListQuestions(number));
        rb_a.setText(celib.CompEssentialsgetChoicea(number));
        rb_b.setText(celib.CompEssentialsgetChoiceb(number));
        rb_c.setText(celib.CompEssentialsgetChoicec(number));
        answer = celib.CompEssentialsgetCorrectAnswer(number);
        number++;

        rb_a.setChecked(false);
        rb_b.setChecked(false);
        rb_c.setChecked(false);
    }

    public void updateScore(int point){
        tv_score.setText("Score: "+ score);
    }


    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }
}
