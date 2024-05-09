package com.example.nbaguessv3;

import static com.example.nbaguessv3.MainActivity.Hint;
import static com.example.nbaguessv3.MainActivity.PlayerGuessed;
import static com.example.nbaguessv3.MainActivity.areAgesWithinTwo;
import static com.example.nbaguessv3.MainActivity.areHeightsWithinTwoInches;
import static com.example.nbaguessv3.MainActivity.guessButton;
import static com.example.nbaguessv3.MainActivity.guessNum;
import static com.example.nbaguessv3.MainActivity.isOneAgeGreater;
import static com.example.nbaguessv3.MainActivity.isOneHeightGreater;
import static com.example.nbaguessv3.MainActivity.isShorterStringInLongerString;
import static com.example.nbaguessv3.MainActivity.player;
import static com.example.nbaguessv3.MainActivity.qe;
import static com.example.nbaguessv3.MainActivity.re;
import static com.example.nbaguessv3.MainActivity.toImage;
import static com.example.nbaguessv3.MainActivity.updateGameStats;
import static com.example.nbaguessv3.MainActivity.updateStatsTextView;
import static com.example.nbaguessv3.MainActivity.win;
import static com.example.nbaguessv3.R.layout.costum_alert_dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class GuessAdapter extends RecyclerView.Adapter<GuessAdapter.ViewHolder> {

    private List<Player> players;
    private Context mContext;



    public GuessAdapter(List<Player> players, Context context) {
        this.players = players;
        mContext = context;
    }

    public void clear() {
        int size = players.size();
        players.clear();
        notifyItemRangeRemoved(0, size);
    }


    public List<Player> getNewData() {
        List<Player> newList = new ArrayList<>();
        return newList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.guess_xml, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Player player1 = players.get(position);
        holder.playerNameTextView.setText(player1.getPlayerName());
        holder.playerPositionTextView.setText(player1.getPosition());
        holder.playerTeamTextView.setText(player1.getTeam());
        holder.playerAgeTextView.setText(Integer.toString(player1.getAge()));
        holder.playerHeightTextView.setText(player1.getHeight());
        holder.playerConfTextView.setText(player1.getConf());
        holder.playerDivTextView.setText(player1.getDiv());
        holder.teamImageView.setImageDrawable(mContext.getDrawable(mContext.getResources().getIdentifier(player1.getTeam().toLowerCase(), "drawable", mContext.getPackageName())));
        holder.playerNumberTextView.setText(player1.getNum());

        if (guessNum <= 8) {

            if (player.getPlayerName().equals(player1.getPlayerName()) && !win) {
                Hint.setText("SHOW RESULT");
                win = true;

                PlayerGuessed.setHint("You solved it in " + (guessNum) + "!");
                guessButton.setText("RESTART");
                PlayerGuessed.setFocusable(false);
                PlayerGuessed.setCursorVisible(false);
                holder.playerNameTextView.setTextColor(Color.parseColor("#FFFFFF"));
                holder.item.setBackgroundResource(R.drawable.bg_fade);
                if (re == 0) {
                    re = 1;
                    updateGameStats(true);
                    updateStatsTextView();
                    View customDialogView = LayoutInflater.from(mContext).inflate(costum_alert_dialog, null);
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setView(customDialogView);
                    ImageView alertImage = customDialogView.findViewById(R.id.alert_image);
                    alertImage.setImageDrawable(mContext.getDrawable(mContext.getResources().getIdentifier(toImage(player.getPlayerName().toLowerCase()), "drawable", mContext.getPackageName())));
                    alertImage.setAdjustViewBounds(true);
                    TextView alertText = customDialogView.findViewById(R.id.alert_text);
                    alertText.setText("Good job, You Won!\nThe Player Was " + player.getPlayerName());
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }

            if (player.getPosition().equals(player1.getPosition())) {
                holder.playerPositionTextView.setBackgroundColor(Color.parseColor("#91C483"));
                holder.playerPositionTextView.setTextColor(Color.parseColor("#FFFFFF"));
            } else {
                if (isShorterStringInLongerString(player.getPosition(), player1.getPosition())) {
                    holder.playerPositionTextView.setTextColor(Color.parseColor("#000000"));
                    holder.playerPositionTextView.setBackgroundColor(Color.parseColor("#FFBF00"));
                }
                else{
                    holder.playerPositionTextView.setTextColor(Color.parseColor("#000000"));
                    holder.playerPositionTextView.setBackgroundColor(Color.parseColor("#98DFD6"));
                }

            }

            if (player.getTeam().equals(player1.getTeam())) {
                holder.playerTeamTextView.setBackgroundColor(Color.parseColor("#91C483"));
                holder.teamImageView.setBackgroundColor(Color.parseColor("#91C483"));
                holder.playerTeamTextView.setTextColor(Color.parseColor("#FFFFFF"));
            }
            else{
                holder.playerTeamTextView.setBackgroundColor(Color.parseColor("#98DFD6"));
                holder.teamImageView.setBackgroundColor(Color.parseColor("#98DFD6"));
                holder.playerTeamTextView.setTextColor(Color.parseColor("#000000"));
            }


            if (player.getAge() == player1.getAge()) {
                holder.playerAgeTextView.setBackgroundColor(Color.parseColor("#91C483"));
                holder.playerAgeTextView.setTextColor(Color.parseColor("#FFFFFF"));
            } else {
                if (Math.abs(player.getAge() - player1.getAge()) <= 2) {
                    holder.playerAgeTextView.setBackgroundColor(Color.parseColor("#FFBF00"));
                    if (player.getAge() > player1.getAge())
                        holder.playerAgeTextView.setText(Integer.toString(player1.getAge()) + "\n" + "↑");
                    else
                        holder.playerAgeTextView.setText(Integer.toString(player1.getAge()) + "\n" + "↓");
                }
                else{
                    holder.playerAgeTextView.setBackgroundColor(Color.parseColor("#98DFD6"));
                    holder.playerAgeTextView.setTextColor(Color.parseColor("#000000"));
                    if (player.getAge() > player1.getAge())
                        holder.playerAgeTextView.setText(Integer.toString(player1.getAge()) + "\n" + "↑");
                    else
                        holder.playerAgeTextView.setText(Integer.toString(player1.getAge()) + "\n" + "↓");
                }
            }

            if (player.getNum().equals(player1.getNum())) {
                holder.playerNumberTextView.setBackgroundColor(Color.parseColor("#91C483"));
                holder.playerNumberTextView.setTextColor(Color.parseColor("#FFFFFF"));
            } else {
                if (areAgesWithinTwo(player.getNum(), player1.getNum())) {
                    if (isOneAgeGreater(player.getNum(), player1.getNum())) {
                        holder.playerNumberTextView.setText(holder.playerNumberTextView.getText().toString() + "\n" + "↑");
                        holder.playerNumberTextView.setBackgroundColor(Color.parseColor("#FFBF00"));
                    }
                    else {
                        holder.playerNumberTextView.setText(holder.playerNumberTextView.getText().toString() + "\n" + "↓");
                        holder.playerNumberTextView.setBackgroundColor(Color.parseColor("#FFBF00"));
                    }
                }
                else{
                    holder.playerNumberTextView.setBackgroundColor(Color.parseColor("#98DFD6"));
                    holder.playerNumberTextView.setTextColor(Color.parseColor("#000000"));
                    if (isOneAgeGreater(player.getNum(), player1.getNum()))
                        holder.playerNumberTextView.setText(holder.playerNumberTextView.getText().toString() + "\n" + "↑");
                    else
                        holder.playerNumberTextView.setText(holder.playerNumberTextView.getText().toString() + "\n" + "↓");
                }
            }

            if (player.getConf().equals(player1.getConf())) {
                holder.playerConfTextView.setBackgroundColor(Color.parseColor("#91C483"));
                holder.playerConfTextView.setTextColor(Color.parseColor("#FFFFFF"));
            }
            else{
                holder.playerConfTextView.setBackgroundColor(Color.parseColor("#98DFD6"));
                holder.playerConfTextView.setTextColor(Color.parseColor("#000000"));
            }

            if (player.getDiv().equals(player1.getDiv())) {
                holder.playerDivTextView.setBackgroundColor(Color.parseColor("#91C483"));
                holder.playerDivTextView.setTextColor(Color.parseColor("#FFFFFF"));
            }
            else{
                holder.playerDivTextView.setBackgroundColor(Color.parseColor("#98DFD6"));
                holder.playerDivTextView.setTextColor(Color.parseColor("#000000"));
            }

            if (player.getHeight().equals(player1.getHeight())) {
                holder.playerHeightTextView.setBackgroundColor(Color.parseColor("#91C483"));
                holder.playerHeightTextView.setTextColor(Color.parseColor("#FFFFFF"));
            } else {
                if (areHeightsWithinTwoInches(player.getHeight(), player1.getHeight())) {
                    if (isOneHeightGreater(player.getHeight(), player1.getHeight())) {
                        holder.playerHeightTextView.setText(holder.playerHeightTextView.getText().toString() + "\n" + "↑");
                        holder.playerHeightTextView.setBackgroundColor(Color.parseColor("#FFBF00"));
                    }
                    else {
                        holder.playerHeightTextView.setText(holder.playerHeightTextView.getText().toString() + "\n" + "↓");
                        holder.playerHeightTextView.setBackgroundColor(Color.parseColor("#FFBF00"));
                    }
                }
                else{
                    holder.playerHeightTextView.setBackgroundColor(Color.parseColor("#98DFD6"));
                    holder.playerHeightTextView.setTextColor(Color.parseColor("#000000"));
                    if (isOneHeightGreater(player.getHeight(), player1.getHeight()))
                        holder.playerHeightTextView.setText(holder.playerHeightTextView.getText().toString() + "\n" + "↑");
                    else
                        holder.playerHeightTextView.setText(holder.playerHeightTextView.getText().toString() + "\n" + "↓");
                }
            }

            if (!win)
                PlayerGuessed.setHint("Guess " + (guessNum+1) + " of 8");

            if ((guessNum == 8 && !(player.getPlayerName().equals(player1.getPlayerName()))) && !win) {
                PlayerGuessed.setHint("You lost!");
                if (qe == 0) {
                    Hint.setText("SHOW RESULT");
                    updateGameStats(win);
                    updateStatsTextView();
                    View customDialogView = LayoutInflater.from(mContext).inflate(costum_alert_dialog, null);
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setView(customDialogView);
                    ImageView alertImage = customDialogView.findViewById(R.id.alert_image);
                    alertImage.setImageDrawable(mContext.getDrawable(mContext.getResources().getIdentifier(toImage(player.getPlayerName().toLowerCase()), "drawable", mContext.getPackageName())));
                    alertImage.setAdjustViewBounds(true);
                    TextView alertText = customDialogView.findViewById(R.id.alert_text);
                    alertText.setText("You Lost.\nThe Player Was " + player.getPlayerName());
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    PlayerGuessed.setFocusable(false);
                    PlayerGuessed.setCursorVisible(false);
                    guessButton.setText("RESTART");
                    qe = 1;
                }
            }
        }



    }


    @Override
    public int getItemCount() {
        return players.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView playerNameTextView;
        public TextView playerNumberTextView;
        public TextView playerPositionTextView;
        public TextView playerTeamTextView;
        public TextView playerHeightTextView;
        public TextView playerDivTextView;
        public TextView playerConfTextView;
        public TextView playerAgeTextView;
        public ImageView teamImageView;
        public ConstraintLayout item;

        public ViewHolder(View view) {
            super(view);
            playerNameTextView = view.findViewById(R.id.PlayerNameTextView);
            playerPositionTextView = view.findViewById(R.id.PlayerPositionTextView);
            playerTeamTextView = view.findViewById(R.id.PlayerTeamTextView);
            playerHeightTextView = view.findViewById(R.id.PlayerHeightTextView);
            playerNumberTextView = view.findViewById(R.id.PlayerNumberTextView);
            playerDivTextView = view.findViewById(R.id.PlayerDivTextView);
            playerConfTextView = view.findViewById(R.id.PlayerConfTextView);
            playerAgeTextView = view.findViewById(R.id.PlayerAgeTextView);
            teamImageView = view.findViewById(R.id.TeamImageView);
            item = (ConstraintLayout) view.findViewById(R.id.guess_layout);

        }
    }

}