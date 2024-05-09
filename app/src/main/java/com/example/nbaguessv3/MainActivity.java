package com.example.nbaguessv3;

import static com.example.nbaguessv3.R.layout.costum_alert_dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    public static boolean win = false;

    private static SharedPreferences sharedPreferences;


    public static int guessNum = 0;

    public static int re = 0;
    public static int qe = 0;

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);


    String[] PlayerNames = {

            /// BOS PLAYERS
            "Al Horford", "Blake Griffin", "Derrick White", "Grant Williams", "Jaylen Brown", "Jayson Tatum", "Malcolm Brogdon", "Marcus Smart", "Payton Pritchard",
            "Robert Williams III", "Sam Hauser",
            /// BOS PLAYERS

            /// BKN PLAYERS
            "Ben Simmons", "Cam Thomas", "Cameron Johnson", "Dorian Finney Smith", "Joe Harris", "Mikal Bridges", "Nicolas Claxton", "Patty Mills", "Royce Oneale",
            "Seth Curry", "Spencer Dinwiddie", "Yuta Watanabe",
            /// BKN PLAYERS

            /// GSW PLAYERS
            "Stephen Curry", "Klay Thompson", "Andrew Wiggins", "Draymond Green", "Kevon Looney", "Jordan Poole", "Andre Iguodala", "Donte DiVincenzo", "Gary Payton II",
            "Moses Moody", "Jonathan Kuminga",
            /// GSW PLAYERS

            /// MEM PLAYERS
            "Steven Adams", "Desmond Bane", "Dillon Brooks", "Brandon Clarke", "Jaren Jackson Jr", "Tyus Jones", "Luke Kennard", "Ja Morant", "Xavier Tillman",
            /// MEM PLAYERS

            /// WAS PLAYERS
            "Deni Avdija", "Bradley Beal", "Kendrick Nunn", "Kristaps Porzingis", "Kyle Kuzma", "Monte Morris", "Will Barton",
            /// WAS PLAYERS

            /// UTA PLAYERS
            "Collin Sexton", "Juan Toscano Anderson", "Jordan Clarkson", "Kelly Olynyk", "Kris Dunn", "Lauri Markkanen", "Rudy Gay", "Talen Horton Tucker",
            "Walker Kessler",
            /// UTA PLAYERS

            /// LAL PLAYERS
            "LeBron James", "Anthony Davis", "Austin Reaves", "Dangelo Russell", "Dennis Schroder", "Jarred Vanderbilt", "Lonnie Walker IV", "Malik Beasley",
            "Mo Bamba", "Rui Hachimura",
            /// LAL PLAYERS

            /// NYK PLAYERS
            "Derrick Rose", "Evan Fournier", "Immanuel Quickley", "Jalen Brunson", "Jericho Sims", "Josh Hart", "Julius Randle", "Mitchell Robinson",
            "Obi Toppin", "Quentin Grimes", "RJ Barrett",
            /// NYK PLAYERS

            /// PHI PLAYERS
            "Danuel House Jr", "DeAnthony Melton", "Georges Niang", "James Harden", "Joel Embiid", "Montrezl Harrell", "PJ Tucker", "Shake Milton",
            "Tobias Harris", "Tyrese Maxey",
            /// PHI PLAYERS

            /// TOR PLAYERS
            "Chris Boucher", "Fred VanVleet", "Gary Trent Jr", "Jakob Poeltl", "Juancho Hernangomez", "OG Anunoby", "Otto Porter Jr", "Pascal Siakam",
            "Precious Achiuwa", "Scottie Barnes", "Thaddeus Young",
            ///TOR PLAYERS

            ///CLE PLAYERS
            "Caris LeVert", "Cedi Osman", "Darius Garland", "Donovan Mitchell", "Evan Mobley", "Isaac Okoro", "Jarrett Allen", "Ricky Rubio", "Robin Lopez",
            ///CLE PLAYERS

            ///CHI PLAYERS
            "Alex Caruso", "Andre Drummond", "Ayo Dosunmu", "Coby White", "Demar DeRozan", "Derrick Jones Jr", "Nikola Vucevic", "Patrick Beverley",
            "Patrick Williams", "Zach LaVine",
            ///CHI PLAYERS

            ///DET PLAYERS
            "Alec Burks", "Bojan Bogdanovic", "Cade Cunningham", "Isaiah Stewart", "Jaden Ivey", "James Wiseman", "Killian Hayes", "Marvin Bagley III",
            "Jalen Duren",
            ///DET PLAYERS

            ///IND PLAYERS
            "Bennedict Mathurin", "Buddy Hield", "Chris Duarte", "Daniel Theis", "George Hill", "Isaiah Jackson", "Jalen Smith", "Myles Turner",
            "TJ McConnell", "Tyrese Haliburton",
            ///IND PLAYERS

            /// MIL PLAYERS
            "Bobby Portis", "Brook Lopez", "Giannis Antetokounmpo", "Goran Dragic", "Grayson Allen", "Jae Crowder", "Jevon Carter", "Joe Ingles",
            "Jrue Holiday", "Khris Middleton", "Pat Connaughton", "Wesley Matthews",
            /// MIL PLAYERS

            /// ATL PLAYERS
            "Bogdan Bogdanovic", "Clint Capela", "DeAndre Hunter", "Dejounte Murray", "John Collins", "Onyeka Okongwu", "Saddiq Bey", "Trae Young",
            /// ATL PLAYERS

            ///CHA PLAYERS
            "Dennis Smith Jr", "Gordon Hayward", "Kelly Oubre Jr", "LaMelo Ball", "PJ Washington", "Terry Rozier",
            ///CHA PLAYERS

            ///LAC PLAYERS
            "Mason Plumlee", "Bones Hyland", "Eric Gordon", "Ivica Zubac", "Kawhi Leonard", "Marcus Morris Sr", "Nicolas Batum", "Norman Powell",
            "Paul George", "Robert Covington", "Russell Westbrook", "Terance Mann",
            ///LAC PLAYERS

            ///MIA PLAYERS
            "Bam Adebayo", "Caleb Martin", "Cody Zeller", "Duncan Robinson", "Gabe Vincent", "Jimmy Butler", "Kevin Love", "Kyle Lowry", "Max Strus",
            "Tyler Herro", "Victor Oladipo",
            ///MIA PLAYERS

            ///ORL PLAYERS
            "Bol Bol", "Cole Anthony", "Franz Wagner", "Gary Harris", "Jalen Suggs", "Markelle Fultz", "Michael Carter Williams", "Paolo Banchero",
            "Wendell Carter Jr",
            ///ORL PLAYERS

            ///DAL PLAYERS
            "Christian Wood", "Davis Bertans", "Dwight Powell", "Facundo Campazzo", "Frank Ntilikina", "JaVale McGee", "Josh Green", "Kyrie Irving",
            "Luka Doncic", "Markieff Morris", "Maxi Kleber", "Reggie Bullock", "Tim Hardaway Jr",
            ///DAL PLAYERS

            ///HOU PLAYERS
            "Alperen Sengun", "Boban Marjanovic", "Frank Kaminsky", "Jabari Smith", "JaeSean Tate", "Jalen Green", "Kenyon Martin Jr", "Kevin Porter Jr",
            ///HOU PLAYERS

            ///NOP PLAYERS
            "Brandon Ingram", "CJ McCollum", "Herbert Jones", "Jonas Valanciunas", "Jose Alvarado", "Josh Richardson", "Larry Nance Jr", "Trey Murphy",
            "Zion Williamson",
            ///NOP PLAYERS

            ///SAS PLAYERS
            "Devin Vassell", "Devonte Graham", "Gorgui Dieng", "Jeremy Sochan", "Keldon Johnson", "Tre Jones", "Zach Collins",
            ///SAS PLAYERS

            ///SAC PLAYERS
            "Davion Mitchell", "DeAaron Fox", "Domantas Sabonis", "Harrison Barnes", "Keegan Murray", "Kevin Huerter", "Malik Monk", "Richaun Holmes",
            "Trey Lyles",
            ///SAC PLAYERS

            ///DEN PLAYERS
            "Aaron Gordon", "Bruce Brown", "DeAndre Jordan", "Jamal Murray", "Jeff Green", "Kentavious Caldwell Pope", "Michael Porter Jr", "Nikola Jokic",
            "Reggie Jackson",
            ///DEN PLAYERS

            ///MIN PLAYERS
            "Anthony Edwards", "Austin Rivers", "Jaden McDaniels", "Jaylen Nowell", "Karl Anthony Towns", "Kyle Anderson", "Mike Conley",
            "Nickeil Alexander Walker", "Rudy Gobert", "Taurean Prince",
            ///MIN PLAYERS

            ///POR PLAYERS
            "Anfernee Simons", "Cam Reddish", "Damian Lillard", "Jerami Grant", "Justise Winslow", "Jusuf Nurkic", "Kevin Knox II", "Matisse Thybulle",
            "Shaedon Sharpe",
            ///POR PLAYERS

            ///OKC PLAYERS
            "Aleksej Pokusevski", "Dario Saric", "Jalen Williams", "Josh Giddey", "Luguentz Dort", "Shai Gilgeous Alexander", "Tre Mann",
            ///OKC PLAYERS

            ///PHX PLAYERS
            "Kevin Durant", "Bismack Biyombo", "Cameron Payne", "Chris Paul", "Damion Lee", "Deandre Ayton", "Devin Booker", "Landry Shamet", "TJ Warren",
            "Terrence Ross", "Torrey Craig"
            ///PHX PLAYERS
    };

    private ConstraintLayout cons;

    public static Player player;

    public static Button guessButton;

    String[] PlayersGuessed = {null, null, null, null, null, null, null, null};

    public static AutoCompleteTextView PlayerGuessed;

    /// BOS PLAYERS
    Player Al_Horford = new Player("Al Horford", "BOS", "C", "42", calculateAge(format.parse("1986-06-03")), "6′9″", "Atl.", "East");
    Player Blake_Griffin = new Player("Blake Griffin", "BOS", "F", "91", calculateAge(format.parse("1989-03-16")), "6′9″", "Atl.", "East");
    Player Derrick_White = new Player("Derrick White", "BOS", "G", "9", calculateAge(format.parse("1994-07-02")), "6′4″", "Atl.", "East");
    Player Grant_Williams = new Player("Grant Williams", "BOS", "F", "12", calculateAge(format.parse("1998-11-30")), "6′5″", "Atl.", "East");
    Player Jaylen_Brown = new Player("Jaylen Brown", "BOS", "F-G", "7", calculateAge(format.parse("1998-03-03")), "6′6″", "Atl.", "East");
    Player Jayson_Tatum = new Player("Jayson Tatum", "BOS", "F", "0", calculateAge(format.parse("1996-10-24")), "6′8″", "Atl.", "East");
    Player Malcolm_Brogdon = new Player("Malcolm Brogdon", "BOS", "G", "13", calculateAge(format.parse("1992-12-11")), "6′5″", "Atl.", "East");
    Player Marcus_Smart = new Player("Marcus Smart", "BOS", "G", "36", calculateAge(format.parse("1994-03-06")), "6′3″", "Atl.", "East");
    Player Payton_Pritchard = new Player("Payton Pritchard", "BOS", "G", "11", calculateAge(format.parse("1998-01-28")), "6′2″", "Atl.", "East");
    Player Robert_Williams_III = new Player("Robert Williams III", "BOS", "C", "44", calculateAge(format.parse("1997-10-17")), "6′8″", "Atl.", "East");
    Player Sam_Hauser = new Player("Sam Hauser", "BOS", "F", "30", calculateAge(format.parse("1997-12-08")), "6′8″", "Atl.", "East");
    /// BOS PLAYERS

    ///BKN PLAYERS
    Player Ben_Simmons = new Player("Ben Simmons", "BKN", "F-G", "10", calculateAge(format.parse("1996-06-20")), "6′10″", "Atl.", "East");
    Player Cam_Thomas = new Player("Cam Thomas", "BKN", "G", "24", calculateAge(format.parse("2001-10-13")), "6′4″", "Atl.", "East");
    Player Cameron_Johnson = new Player("Cameron Johnson", "BKN", "F", "2", calculateAge(format.parse("1996-03-03")), "6′8″", "Atl.", "East");
    Player Dorian_Finney_Smith = new Player("Dorian Finney Smith", "BKN", "F", "28", calculateAge(format.parse("1993-05-04")), "6′7″", "Atl.", "East");
    Player Joe_Harris = new Player("Joe Harris", "BKN", "G", "12", calculateAge(format.parse("1991-09-06")), "6′6″", "Atl.", "East");
    Player Mikal_Bridges = new Player("Mikal Bridges", "BKN", "F", "1", calculateAge(format.parse("1996-08-30")), "6′6″", "Atl.", "East");
    Player Nicolas_Claxton = new Player("Nicolas Claxton", "BKN", "C", "33", calculateAge(format.parse("1999-04-17")), "6′11″", "Atl.", "East");
    Player Patty_Mills = new Player("Patty Mills", "BKN", "G", "8", calculateAge(format.parse("1988-08-11")), "6′1″", "Atl.", "East");
    Player Royce_Oneale = new Player("Royce Oneale", "BKN", "F-G", "00", calculateAge(format.parse("1993-06-05")), "6′4″", "Atl.", "East");
    Player Seth_Curry = new Player("Seth Curry", "BKN", "G", "30", calculateAge(format.parse("1990-08-23")), "6′2″", "Atl.", "East");
    Player Spencer_Dinwiddie = new Player("Spencer Dinwiddie", "BKN", "G", "26", calculateAge(format.parse("1993-04-03")), "6′5″", "Atl.", "East");
    Player Yuta_Watanabe = new Player("Yuta Watanabe", "BKN", "F", "18", calculateAge(format.parse("1994-10-13")), "6′8″", "Atl.", "East");
    ///BKN PLAYERS

    /// GSW PLAYERS
    Player Steph_Curry = new Player("Stephen Curry", "GSW", "G", "30", calculateAge(format.parse("1988-03-14")), "6′3″", "Pac.", "West");
    Player Klay_Thompson = new Player("Klay Thompson", "GSW", "G", "11", calculateAge(format.parse("1990-02-08")), "6′6″", "Pac.", "West");
    Player Andrew_Wiggins = new Player("Andrew Wiggins", "GSW", "F", "22", calculateAge(format.parse("1995-02-23")), "6′7″", "Pac.", "West");
    Player Draymond_Green = new Player("Draymond Green", "GSW", "F", "23", calculateAge(format.parse("1990-03-04")), "6′6″", "Pac.", "West");
    Player Kevon_Looney = new Player("Kevon Looney", "GSW", "C", "5", calculateAge(format.parse("1996-02-06")), "6′9″", "Pac.", "West");
    Player Jordan_Poole = new Player("Jordan Poole", "GSW", "G", "3", calculateAge(format.parse("1999-06-19")), "6′4″", "Pac.", "West");
    Player Andre_Iguodala = new Player("Andre Iguodala", "GSW", "F", "9", calculateAge(format.parse("1984-01-28")), "6′7″", "Pac.", "West");
    Player Donte_DiVincenzo = new Player("Donte DiVincenzo", "GSW", "G", "0", calculateAge(format.parse("1997-01-31")), "6′4″", "Pac.", "West");
    Player Gary_Payton_II = new Player("Gary Payton II", "GSW", "G", "8", calculateAge(format.parse("1992-12-01")), "6′3″", "Pac.", "West");
    Player Moses_Moody = new Player("Moses Moody", "GSW", "G", "4", calculateAge(format.parse("2002-05-31")), "6′5″", "Pac.", "West");
    Player Jonathan_Kuminga = new Player("Jonathan Kuminga", "GSW", "F", "00", calculateAge(format.parse("2002-10-06")), "6′6″", "Pac.", "West");
    /// GSW PLAYERS

    /// MEM PLAYERS
    Player Steven_Adams = new Player("Steven Adams", "MEM", "C", "4", calculateAge(format.parse("1993-07-20")), "6′11″", "SW", "West");
    Player Desmond_Bane = new Player("Desmond Bane", "MEM", "G", "22", calculateAge(format.parse("1998-06-25")), "6′6″", "SW", "West");
    Player Dillon_Brooks = new Player("Dillon Brooks", "MEM", "F-G", "24", calculateAge(format.parse("1996-01-22")), "6′7″", "SW", "West");
    Player Brandon_Clarke = new Player("Brandon Clarke", "MEM", "F", "15", calculateAge(format.parse("1996-09-19")), "6′8″", "SW", "West");
    Player Jaren_Jackson_Jr = new Player("Jaren Jackson Jr", "MEM", "F-C", "13", calculateAge(format.parse("1999-09-15")), "6′11″", "SW", "West");
    Player Tyus_Jones = new Player("Tyus Jones", "MEM", "G", "21", calculateAge(format.parse("1996-05-10")), "6′0″", "SW", "West");
    Player Luke_Kennard = new Player("Luke Kennard", "MEM", "G", "10", calculateAge(format.parse("1996-06-24")), "6′5″", "SW", "West");
    Player Ja_Morant = new Player("Ja Morant", "MEM", "G", "12", calculateAge(format.parse("1999-08-10")), "6′3″", "SW", "West");
    Player Xavier_Tillman = new Player("Xavier Tillman", "MEM", "F", "2", calculateAge(format.parse("1999-01-12")), "6′8″", "SW", "West");
    /// MEM PLAYERS

    /// WAS PLAYERS
    Player Deni_Avdija = new Player("Deni Avdija", "WAS", "F", "9", calculateAge(format.parse("2001-01-03")), "6′9″", "SE", "East");
    Player Bradley_Beal = new Player("Bradley Beal", "WAS", "G", "3", calculateAge(format.parse("1993-06-28")), "6′3″", "SE", "East");
    Player Kendrick_Nunn = new Player("Kendrick Nunn", "WAS", "G", "20", calculateAge(format.parse("1995-08-03")), "6′2″", "SE", "East");
    Player Kristaps_Porzingis = new Player("Kristaps Porzingis", "WAS", "F-C", "6", calculateAge(format.parse("1995-08-02")), "7′3″", "SE", "East");
    Player Kyle_Kuzma = new Player("Kyle Kuzma", "WAS", "F", "33", calculateAge(format.parse("1995-07-24")), "6′8″", "SE", "East");
    Player Monte_Morris = new Player("Monte Morris", "WAS", "G", "22", calculateAge(format.parse("1995-07-27")), "6′2″", "SE", "East");
    Player Will_Barton = new Player("Will Barton", "WAS", "G", "1", calculateAge(format.parse("1991-01-06")), "6′5″", "SE", "East");
    /// WAS PLAYERS

    /// UTA PLAYERS
    Player Juan_Toscano_Anderson = new Player("Juan Toscano Anderson", "UTA", "F", "95", calculateAge(format.parse("1993-04-10")), "6′6″", "NW", "West");
    Player Collin_Sexton = new Player("Collin Sexton", "UTA", "G", "2", calculateAge(format.parse("1999-01-04")), "6′1″", "NW", "West");
    Player Jordan_Clarkson = new Player("Jordan Clarkson", "UTA", "G", "00", calculateAge(format.parse("1992-06-07")), "6′4″", "NW", "West");
    Player Kelly_Olynyk = new Player("Kelly Olynyk", "UTA", "F-C", "41", calculateAge(format.parse("1991-04-19")), "6′11″", "NW", "West");
    Player Kris_Dunn = new Player("Kris Dunn", "UTA", "G", "11", calculateAge(format.parse("1994-03-18")), "6′3″", "NW", "West");
    Player Lauri_Markkanen = new Player("Lauri Markkanen", "UTA", "F", "23", calculateAge(format.parse("1997-05-22")), "7′0″", "NW", "West");
    Player Rudy_Gay = new Player("Rudy Gay", "UTA", "F", "22", calculateAge(format.parse("1986-08-17")), "6′8″", "NW", "West");
    Player Talen_Horton_Tucker = new Player("Talen Horton Tucker", "UTA", "G", "0", calculateAge(format.parse("2000-11-25")), "6′4″", "NW", "West");
    Player Walker_Kessler = new Player("Walker Kessler", "UTA", "F-C", "24", calculateAge(format.parse("2001-07-26")), "7′1″", "NW", "West");
    /// UTA PLAYERS

    /// LAL PLAYERS
    Player LeBron_James = new Player("LeBron James", "LAL", "F", "6", calculateAge(format.parse("1984-12-30")), "6′9″", "Pac.", "West");
    Player Anthony_Davis = new Player("Anthony Davis", "LAL", "F-C", "3", calculateAge(format.parse("1993-03-11")), "6′10″", "Pac.", "West");
    Player Austin_Reaves = new Player("Austin Reaves", "LAL", "G", "15", calculateAge(format.parse("1998-05-29")), "6′5″", "Pac.", "West");
    Player Dangelo_Russell = new Player("Dangelo Russell", "LAL", "G", "1", calculateAge(format.parse("1996-02-23")), "6′4″", "Pac.", "West");
    Player Dennis_Schroder = new Player("Dennis Schroder", "LAL", "G", "17", calculateAge(format.parse("1993-09-15")), "6′1″", "Pac.", "West");
    Player Jarred_Vanderbilt = new Player("Jarred Vanderbilt", "LAL", "F", "17", calculateAge(format.parse("1999-04-03")), "6′9″", "Pac.", "West");
    Player Lonnie_Walker_IV = new Player("Lonnie Walker IV", "LAL", "G", "4", calculateAge(format.parse("1998-12-14")), "6′5″", "Pac.", "West");
    Player Malik_Beasley = new Player("Malik Beasley", "LAL", "G", "5", calculateAge(format.parse("1996-11-26")), "6′4″", "Pac.", "West");
    Player Mo_Bamba = new Player("Mo Bamba", "LAL", "C", "12", 26, "7′0″", "Pac.", "West");
    Player Rui_Hachimura = new Player("Rui Hachimura", "LAL", "F", "28", calculateAge(format.parse("1998-02-08")), "6′8″", "Pac.", "West");
    /// LAL PLAYERS

    /// NYK PLAYERS
    Player Derrick_Rose = new Player("Derrick Rose", "NYK", "G", "4", calculateAge(format.parse("1988-10-04")), "6′2″", "Atl.", "East");
    Player Evan_Fournier = new Player("Evan Fournier", "NYK", "G", "13", calculateAge(format.parse("1992-10-29")), "6′7″", "Atl.", "East");
    Player Immanuel_Quickley = new Player("Immanuel Quickley", "NYK", "G", "5", calculateAge(format.parse("1999-06-17")), "6′3″", "Atl.", "East");
    Player Jalen_Brunson = new Player("Jalen Brunson", "NYK", "G", "11", calculateAge(format.parse("1996-08-31")), "6′1″", "Atl.", "East");
    Player Jericho_Sims = new Player("Jericho Sims", "NYK", "C", "45", calculateAge(format.parse("1998-10-20")), "6′10″", "Atl.", "East");
    Player Josh_Hart = new Player("Josh Hart", "NYK", "G", "3", calculateAge(format.parse("1995-03-06")), "6′5″", "Atl.", "East");
    Player Julius_Randle = new Player("Julius Randle", "NYK", "F", "30", calculateAge(format.parse("1994-11-29")), "6′8″", "Atl.", "East");
    Player Mitchell_Robinson = new Player("Mitchell Robinson", "NYK", "C", "23", calculateAge(format.parse("1998-04-01")), "7′0″", "Atl.", "East");
    Player Obi_Toppin = new Player("Obi Toppin", "NYK", "F", "1", calculateAge(format.parse("1998-03-04")), "6′9″", "Atl.", "East");
    Player Quentin_Grimes = new Player("Quentin Grimes", "NYK", "G", "6", calculateAge(format.parse("2000-03-08")), "6′5″", "Atl.", "East");
    Player RJ_Barrett = new Player("RJ Barrett", "NYK", "F-G", "9", calculateAge(format.parse("2000-06-14")), "6′6″", "Atl.", "East");
    /// NYK PLAYERS

    /// PHI PLAYERS
    Player Danuel_House_Jr = new Player("Danuel House Jr", "PHI", "F-G", "25", calculateAge(format.parse("1993-06-07")), "6′6″", "Atl.", "East");
    Player DeAnthony_Melton = new Player("DeAnthony Melton", "PHI", "G", "8", calculateAge(format.parse("1998-05-28")), "6′2″", "Atl.", "East");
    Player Georges_Niang = new Player("Georges Niang", "PHI", "F", "20", calculateAge(format.parse("1993-06-17")), "6′7″", "Atl.", "East");
    Player James_Harden = new Player("James Harden", "PHI", "G", "1", calculateAge(format.parse("1989-08-26")), "6′5″", "Atl.", "East");
    Player Joel_Embiid = new Player("Joel Embiid", "PHI", "C", "21", calculateAge(format.parse("1994-03-16")), "7′0″", "Atl.", "East");
    Player Montrezl_Harrell = new Player("Montrezl Harrell", "PHI", "C", "5", calculateAge(format.parse("1994-01-26")), "6′7″", "Atl.", "East");
    Player PJ_Tucker = new Player("PJ Tucker", "PHI", "F", "17", calculateAge(format.parse("1985-05-05")), "6′5″", "Atl.", "East");
    Player Shake_Milton = new Player("Shake Milton", "PHI", "G", "18", calculateAge(format.parse("1996-09-26")), "6′5″", "Atl.", "East");
    Player Tobias_Harris = new Player("Tobias Harris", "PHI", "F", "12", calculateAge(format.parse("1992-07-15")), "6′8″", "Atl.", "East");
    Player Tyrese_Maxey = new Player("Tyrese Maxey", "PHI", "G", "0", calculateAge(format.parse("2000-11-04")), "6′3″", "Atl.", "East");
    /// PHI PLAYERS

    /// TOR PLAYERS
    Player Chris_Boucher = new Player("Chris Boucher", "TOR", "F", "25", calculateAge(format.parse("1993-01-11")), "6′9″", "Atl.", "East");
    Player Juancho_Hernangomez = new Player("Juancho Hernangomez", "TOR", "F", "41", calculateAge(format.parse("1995-09-28")), "6′9″", "Atl.", "East");
    Player Fred_VanVleet = new Player("Fred VanVleet", "TOR", "G", "23", calculateAge(format.parse("1994-02-25")), "6′1″", "Atl.", "East");
    Player Gary_Trent_Jr = new Player("Gary Trent Jr", "TOR", "G", "33", calculateAge(format.parse("1999-01-18")), "6′5″", "Atl.", "East");
    Player Jakob_Poeltl = new Player("Jakob Poeltl", "TOR", "C", "19", calculateAge(format.parse("1995-10-15")), "7′1″", "Atl.", "East");
    Player OG_Anunoby = new Player("OG Anunoby", "TOR", "F", "3", calculateAge(format.parse("1997-07-17")), "6′7″", "Atl.", "East");
    Player Thaddeus_Young = new Player("Thaddeus Young", "TOR", "F", "21", calculateAge(format.parse("1988-06-21")), "6′8″", "Atl.", "East");
    Player Scottie_Barnes = new Player("Scottie Barnes", "TOR", "F-G", "4", calculateAge(format.parse("2001-08-01")), "6′8″", "Atl.", "East");
    Player Precious_Achiuwa = new Player("Precious Achiuwa", "TOR", "F-C", "5", calculateAge(format.parse("1999-09-19")), "6′9″", "Atl.", "East");
    Player Pascal_Siakam = new Player("Pascal Siakam", "TOR", "F", "43", calculateAge(format.parse("1994-04-02")), "6′9″", "Atl.", "East");
    Player Otto_Porter_Jr = new Player("Otto Porter Jr", "TOR", "F", "32", calculateAge(format.parse("1993-06-03")), "6′8″", "Atl.", "East");
    /// TOR PLAYERS

    ///CLE PLAYERS
    Player Caris_LeVert = new Player("Caris LeVert", "CLE", "G", "3", calculateAge(format.parse("1994-08-25")), "6′6″", "Cen.", "East");
    Player Cedi_Osman = new Player("Cedi Osman", "CLE", "F", "16", calculateAge(format.parse("1995-04-08")), "6′7″", "Cen.", "East");
    Player Darius_Garland = new Player("Darius Garland", "CLE", "G", "10", calculateAge(format.parse("2000-01-26")), "6′1″", "Cen.", "East");
    Player Donovan_Mitchell = new Player("Donovan Mitchell", "CLE", "G", "45", calculateAge(format.parse("1996-09-07")), "6′1″", "Cen.", "East");
    Player Evan_Mobley = new Player("Evan Mobley", "CLE", "C", "4", calculateAge(format.parse("2001-06-18")), "7′0″", "Cen.", "East");
    Player Isaac_Okoro = new Player("Isaac Okoro", "CLE", "G", "35", calculateAge(format.parse("2001-01-26")), "6′6″", "Cen.", "East");
    Player Jarrett_Allen = new Player("Jarrett Allen", "CLE", "C", "31", calculateAge(format.parse("1998-04-21")), "6′11″", "Cen.", "East");
    Player Ricky_Rubio = new Player("Ricky Rubio", "CLE", "G", "13", calculateAge(format.parse("1990-10-21")), "6′3″", "Cen.", "East");
    Player Robin_Lopez = new Player("Robin Lopez", "CLE", "C", "33", calculateAge(format.parse("1988-04-01")), "7′0″", "Cen.", "East");
    ///CLE PLAYERS

    ///CHI PLAYERS
    Player Alex_Caruso = new Player("Alex Caruso", "CHI", "G", "6", calculateAge(format.parse("1994-02-28")), "6′5″", "Cen.", "East");
    Player Andre_Drummond = new Player("Andre Drummond", "CHI", "C", "3", calculateAge(format.parse("1993-08-10")), "6′10″", "Cen.", "East");
    Player Ayo_Dosunmu = new Player("Ayo Dosunmu", "CHI", "G", "12", calculateAge(format.parse("2000-01-17")), "6′4″", "Cen.", "East");
    Player Coby_White = new Player("Coby White", "CHI", "G", "0", calculateAge(format.parse("2000-02-16")), "6′4″", "Cen.", "East");
    Player Demar_DeRozan = new Player("Demar DeRozan", "CHI", "F-G", "11", calculateAge(format.parse("1989-08-07")), "6′6″", "Cen.", "East");
    Player Derrick_Jones_Jr = new Player("Derrick Jones Jr", "CHI", "F", "5", calculateAge(format.parse("1997-02-15")), "6′6″", "Cen.", "East");
    Player Nikola_Vucevic = new Player("Nikola Vucevic", "CHI", "C", "9", calculateAge(format.parse("1990-10-24")), "6′11″", "Cen.", "East");
    Player Patrick_Beverley = new Player("Patrick Beverley", "CHI", "G", "21", calculateAge(format.parse("1988-07-12")), "6′1″", "Cen.", "East");
    Player Patrick_Williams = new Player("Patrick Williams", "CHI", "F", "44", calculateAge(format.parse("2001-08-26")), "6′8″", "Cen.", "East");
    Player Zach_LaVine = new Player("Zach LaVine", "CHI", "G", "8", calculateAge(format.parse("1995-03-10")), "6′6″", "Cen.", "East");
    ///CHI PLAYERS

    ///DET PLAYERS
    Player Alec_Burks = new Player("Alec Burks", "DET", "G", "5", calculateAge(format.parse("1991-07-20")), "6′6″", "Cen.", "East");
    Player Bojan_Bogdanovic = new Player("Bojan Bogdanovic", "DET", "F", "44", calculateAge(format.parse("1989-04-18")), "6′8″", "Cen.", "East");
    Player Cade_Cunningham = new Player("Cade Cunningham", "DET", "G", "2", calculateAge(format.parse("2001-09-25")), "6′8″", "Cen.", "East");
    Player Isaiah_Stewart = new Player("Isaiah Stewart", "DET", "F-C", "28", calculateAge(format.parse("2001-05-22")), "6′9″", "Cen.", "East");
    Player Jaden_Ivey = new Player("Jaden Ivey", "DET", "G", "23", calculateAge(format.parse("2002-02-23")), "6′4″", "Cen.", "East");
    Player James_Wiseman = new Player("James Wiseman", "DET", "C", "13", calculateAge(format.parse("2001-03-31")), "7′1″", "Cen.", "East");
    Player Killian_Hayes = new Player("Killian Hayes", "DET", "G", "7", calculateAge(format.parse("2001-07-21")), "6′5″", "Cen.", "East");
    Player Marvin_Bagley_III = new Player("Marvin Bagley III", "DET", "F", "35", calculateAge(format.parse("1999-03-14")), "6′11″", "Cen.", "East");
    Player Jalen_Duren = new Player("Jalen Duren", "DET", "C", "0", calculateAge(format.parse("2003-11-18")), "6′11″", "Cen.", "East");
    ///DET PLAYERS

    ///IND PLAYERS
    Player Bennedict_Mathurin = new Player("Bennedict Mathurin", "IND", "G", "00", calculateAge(format.parse("2002-06-19")), "6′5″", "Cen.", "East");
    Player Buddy_Hield = new Player("Buddy Hield", "IND", "G", "24", calculateAge(format.parse("1992-12-17")), "6′4″", "Cen.", "East");
    Player Chris_Duarte = new Player("Chris Duarte", "IND", "F", "3", calculateAge(format.parse("1997-06-13")), "6′6″", "Cen.", "East");
    Player Daniel_Theis = new Player("Daniel Theis", "IND", "C", "27", calculateAge(format.parse("1992-04-04")), "6′8″", "Cen.", "East");
    Player George_Hill = new Player("George Hill", "IND", "G", "7", calculateAge(format.parse("1986-05-04")), "6′3″", "Cen.", "East");
    Player Isaiah_Jackson = new Player("Isaiah Jackson", "IND", "F-C", "22", calculateAge(format.parse("2002-01-10")), "6′10″", "Cen.", "East");
    Player Jalen_Smith = new Player("Jalen Smith", "IND", "F", "25", calculateAge(format.parse("2000-03-16")), "6′10″", "Cen.", "East");
    Player Myles_Turner = new Player("Myles Turner", "IND", "C", "33", calculateAge(format.parse("1996-03-24")), "6′11″", "Cen.", "East");
    Player TJ_McConnell = new Player("TJ McConnell", "IND", "G", "9", calculateAge(format.parse("1992-03-25")), "6′1″", "Cen.", "East");
    Player Tyrese_Haliburton = new Player("Tyrese Haliburton", "IND", "G", "0", calculateAge(format.parse("2000-02-29")), "6′5″", "Cen.", "East");
    ///IND PLAYERS

    /// MIL PLAYERS
    Player Bobby_Portis = new Player("Bobby Portis", "MIL", "F-C", "9", calculateAge(format.parse("1995-02-10")), "6′10″", "Cen.", "East");
    Player Brook_Lopez = new Player("Brook Lopez", "MIL", "C", "11", calculateAge(format.parse("1988-04-01")), "7′0″", "Cen.", "East");
    Player Giannis_Antetokounmpo = new Player("Giannis Antetokounmpo", "MIL", "F", "34", calculateAge(format.parse("1994-12-06")), "6′11″", "Cen.", "East");
    Player Goran_Dragic = new Player("Goran Dragic", "MIL", "G", "31", calculateAge(format.parse("1986-05-06")), "6′3″", "Cen.", "East");
    Player Grayson_Allen = new Player("Grayson Allen", "MIL", "G", "12", calculateAge(format.parse("1995-10-08")), "6′4″", "Cen.", "East");
    Player Jae_Crowder = new Player("Jae Crowder", "MIL", "F", "99", calculateAge(format.parse("1990-07-06")), "6′6″", "Cen.", "East");
    Player Jevon_Carter = new Player("Jevon Carter", "MIL", "G", "5", calculateAge(format.parse("1995-09-14")), "6′1″", "Cen.", "East");
    Player Joe_Ingles = new Player("Joe Ingles", "MIL", "F", "7", calculateAge(format.parse("1987-10-02")), "6′7″", "Cen.", "East");
    Player Jrue_Holiday = new Player("Jrue Holiday", "MIL", "G", "21", calculateAge(format.parse("1990-06-12")), "6′3″", "Cen.", "East");
    Player Khris_Middleton = new Player("Khris Middleton", "MIL", "F-G", "22", calculateAge(format.parse("1991-08-12")), "6′7″", "Cen.", "East");
    Player Pat_Connaughton = new Player("Pat Connaughton", "MIL", "G", "24", calculateAge(format.parse("1993-01-06")), "6′5″", "Cen.", "East");
    Player Wesley_Matthews = new Player("Wesley Matthews", "MIL", "G", "23", calculateAge(format.parse("1986-10-14")), "6′4″", "Cen.", "East");
    /// MIL PLAYERS

    /// ATL PLAYERS
    Player Bogdan_Bogdanovic = new Player("Bogdan Bogdanovic", "ATL", "G", "13", calculateAge(format.parse("1992-08-18")), "6′6″", "SE", "East");
    Player Clint_Capela = new Player("Clint Capela", "ATL", "C", "15", calculateAge(format.parse("1994-05-18")), "6′10″", "SE", "East");
    Player DeAndre_Hunter = new Player("DeAndre Hunter", "ATL", "F", "12", calculateAge(format.parse("1997-12-02")), "6′7″", "SE", "East");
    Player Dejounte_Murray = new Player("Dejounte Murray", "ATL", "G", "5", calculateAge(format.parse("1996-09-19")), "6′4″", "SE", "East");
    Player John_Collins = new Player("John Collins", "ATL", "F", "20", calculateAge(format.parse("1997-09-23")), "6′9″", "SE", "East");
    Player Onyeka_Okongwu = new Player("Onyeka Okongwu", "ATL", "F-C", "17", calculateAge(format.parse("2000-12-11")), "6′9″", "SE", "East");
    Player Saddiq_Bey = new Player("Saddiq Bey", "ATL", "F", "41", calculateAge(format.parse("1999-04-09")), "6′8″", "SE", "East");
    Player Trae_Young = new Player("Trae Young", "ATL", "G", "11", calculateAge(format.parse("1998-09-19")), "6′1″", "SE", "East");
    /// ATL PLAYERS

    ///CHA PLAYERS
    Player Dennis_Smith_Jr = new Player("Dennis Smith Jr", "CHA", "G", "8", calculateAge(format.parse("1997-11-25")), "6′2″", "SE", "East");
    Player Gordon_Hayward = new Player("Gordon Hayward", "CHA", "F", "20", calculateAge(format.parse("1990-03-23")), "6′7″", "SE", "East");
    Player Kelly_Oubre_Jr = new Player("Kelly Oubre Jr", "CHA", "F", "12", calculateAge(format.parse("1999-12-09")), "6′7″", "SE", "East");
    Player LaMelo_Ball = new Player("LaMelo Ball", "CHA", "G", "1", calculateAge(format.parse("2001-08-22")), "6′8″", "SE", "East");
    Player PJ_Washington = new Player("PJ Washington", "CHA", "F", "25", calculateAge(format.parse("1998-08-23")), "6′7″", "SE", "East");
    Player Terry_Rozier = new Player("Terry Rozier", "CHA", "G", "3", calculateAge(format.parse("1994-03-17")), "6′1″", "SE", "East");
    ///CHA PLAYERS

    ///MIA PLAYERS
    Player Bam_Adebayo = new Player("Bam Adebayo", "MIA", "C", "13", calculateAge(format.parse("1997-07-18")), "6′9″", "SE", "East");
    Player Caleb_Martin = new Player("Caleb Martin", "MIA", "F-G", "16", calculateAge(format.parse("1995-09-28")), "6′5″", "SE", "East");
    Player Cody_Zeller = new Player("Cody Zeller", "MIA", "C", "44", calculateAge(format.parse("1992-10-05")), "7′0″", "SE", "East");
    Player Duncan_Robinson = new Player("Duncan Robinson", "MIA", "F", "55", calculateAge(format.parse("1994-04-22")), "6′7″", "SE", "East");
    Player Gabe_Vincent = new Player("Gabe Vincent", "MIA", "G", "2", calculateAge(format.parse("1996-06-14")), "6′3″", "SE", "East");
    Player Jimmy_Butler = new Player("Jimmy Butler", "MIA", "F", "22", calculateAge(format.parse("1989-09-14")), "6′7″", "SE", "East");
    Player Kevin_Love = new Player("Kevin Love", "MIA", "F", "42", calculateAge(format.parse("1988-09-07")), "6′8″", "SE", "East");
    Player Kyle_Lowry = new Player("Kyle Lowry", "MIA", "G", "7", calculateAge(format.parse("1986-03-25")), "6′0″", "SE", "East");
    Player Max_Strus = new Player("Max Strus", "MIA", "F", "31", calculateAge(format.parse("1996-03-28")), "6′5″", "SE", "East");
    Player Tyler_Herro = new Player("Tyler Herro", "MIA", "G", "14", calculateAge(format.parse("2000-01-20")), "6′5″", "SE", "East");
    Player Victor_Oladipo = new Player("Victor Oladipo", "MIA", "G", "4", calculateAge(format.parse("1992-05-04")), "6′4″", "SE", "East");
    ///MIA PLAYERS

    ///LAC PLAYERS
    Player Mason_Plumlee = new Player("Mason Plumlee", "LAC", "C", "44", calculateAge(format.parse("1990-05-23")), "6′11″", "Pac.", "West");
    Player Bones_Hyland = new Player("Bones Hyland", "LAC", "G", "5", calculateAge(format.parse("2000-09-14")), "6′3″", "Pac.", "West");
    Player Eric_Gordon = new Player("Eric Gordon", "LAC", "G", "10", calculateAge(format.parse("1988-12-25")), "6′3″", "Pac.", "West");
    Player Ivica_Zubac = new Player("Ivica Zubac", "LAC", "C", "40", calculateAge(format.parse("1997-03-18")), "7′0″", "Pac.", "West");
    Player Kawhi_Leonard = new Player("Kawhi Leonard", "LAC", "F", "2", calculateAge(format.parse("1991-06-29")), "6′7″", "Pac.", "West");
    Player Marcus_Morris_Sr = new Player("Marcus Morris Sr", "LAC", "F", "8", calculateAge(format.parse("1989-09-02")), "6′8″", "Pac.", "West");
    Player Nicolas_Batum = new Player("Nicolas Batum", "LAC", "F", "33", calculateAge(format.parse("1988-12-14")), "6′9″", "Pac.", "West");
    Player Norman_Powell = new Player("Norman Powell", "LAC", "G", "24", calculateAge(format.parse("1993-05-25")), "6′3″", "Pac.", "West");
    Player Paul_George = new Player("Paul George", "LAC", "F", "13", calculateAge(format.parse("1990-05-02")), "6′8″", "Pac.", "West");
    Player Robert_Covington = new Player("Robert Covington", "LAC", "F", "23", calculateAge(format.parse("1990-12-14")), "6′7″", "Pac.", "West");
    Player Russell_Westbrook = new Player("Russell Westbrook", "LAC", "G", "0", calculateAge(format.parse("1988-11-12")), "6′3″", "Pac.", "West");
    Player Terance_Mann = new Player("Terance Mann", "LAC", "G", "14", calculateAge(format.parse("1996-10-18")), "6′5″", "Pac.", "West");
    ///LAC PLAYERS

    ///ORL PLAYERS
    Player Bol_Bol = new Player("Bol Bol", "ORL", "F-C", "10", calculateAge(format.parse("1999-11-16")), "7′2″", "SE", "East");
    Player Cole_Anthony = new Player("Cole Anthony", "ORL", "G", "50", calculateAge(format.parse("2000-05-15")), "6′3″", "SE", "East");
    Player Franz_Wagner = new Player("Franz Wagner", "ORL", "F", "22", calculateAge(format.parse("2001-08-27")), "6′9″", "SE", "East");
    Player Gary_Harris = new Player("Gary Harris", "ORL", "G", "14", calculateAge(format.parse("1994-09-14")), "6′4″", "SE", "East");
    Player Jalen_Suggs = new Player("Jalen Suggs", "ORL", "G", "4", calculateAge(format.parse("2001-06-03")), "6′4″", "SE", "East");
    Player Markelle_Fultz = new Player("Markelle Fultz", "ORL", "G", "20", calculateAge(format.parse("1998-05-29")), "6′3″", "SE", "East");
    Player Michael_Carter_Williams = new Player("Michael Carter Williams", "ORL", "G", "11", calculateAge(format.parse("1991-10-10")), "6′5″", "SE", "East");
    Player Paolo_Banchero = new Player("Paolo Banchero", "ORL", "F", "5", calculateAge(format.parse("2002-11-12")), "6′10″", "SE", "East");
    Player Wendell_Carter_Jr = new Player("Wendell Carter Jr", "ORL", "C", "34", calculateAge(format.parse("1999-04-16")), "6′9″", "SE", "East");
    ///ORL PLAYERS

    ///DAL PLAYERS
    Player Christian_Wood = new Player("Christian Wood", "DAL", "F-C", "35", calculateAge(format.parse("1995-09-27")), "6′11″", "SW", "West");
    Player Davis_Bertans = new Player("Davis Bertans", "DAL", "F", "44", calculateAge(format.parse("1992-11-12")), "6′10″", "SW", "West");
    Player Facundo_Campazzo = new Player("Facundo Campazzo", "DAL", "G", "3", calculateAge(format.parse("1991-03-23")), "5′11″", "SW", "West");
    Player Frank_Ntilikina = new Player("Frank Ntilikina", "DAL", "G", "21", calculateAge(format.parse("1998-07-28")), "6′4″", "SW", "West");
    Player JaVale_McGee = new Player("JaVale McGee", "DAL", "C", "00", calculateAge(format.parse("1988-01-19")), "7′0″", "SW", "West");
    Player Josh_Green = new Player("Josh Green", "DAL", "G", "8", calculateAge(format.parse("2000-11-16")), "6′5″", "SW", "West");
    Player Kyrie_Irving = new Player("Kyrie Irving", "DAL", "G", "2", calculateAge(format.parse("1992-03-23")), "6′2″", "SW", "West");
    Player Luka_Doncic = new Player("Luka Doncic", "DAL", "G", "77", calculateAge(format.parse("1999-02-28")), "6′7″", "SW", "West");
    Player Markieff_Morris = new Player("Markieff Morris", "DAL", "F", "13", calculateAge(format.parse("1989-09-02")), "6′7″", "SW", "West");
    Player Maxi_Kleber = new Player("Maxi Kleber", "DAL", "F", "42", calculateAge(format.parse("1992-01-29")), "6′10″", "SW", "West");
    Player Reggie_Bullock = new Player("Reggie Bullock", "DAL", "F", "25", calculateAge(format.parse("1991-03-16")), "6′6″", "SW", "West");
    Player Tim_Hardaway_Jr = new Player("Tim Hardaway Jr", "DAL", "G", "11", calculateAge(format.parse("1992-03-16")), "6′5″", "SW", "West");
    Player Dwight_Powell = new Player("Dwight Powell", "DAL", "C", "7", calculateAge(format.parse("1991-07-20")), "6′10″", "SW", "West");
    ///DAL PLAYERS

    ///HOU PLAYERS
    Player Alperen_Sengun = new Player("Alperen Sengun", "HOU", "C", "28", calculateAge(format.parse("2002-07-25")), "6′10″", "SW", "West");
    Player Boban_Marjanovic = new Player("Boban Marjanovic", "HOU", "C", "51", calculateAge(format.parse("1988-08-15")), "7′4″", "SW", "West");
    Player Frank_Kaminsky = new Player("Frank Kaminsky", "HOU", "C", "33", calculateAge(format.parse("1993-04-03")), "7′0″", "SW", "West");
    Player Jabari_Smith = new Player("Jabari Smith", "HOU", "F", "1", calculateAge(format.parse("2003-05-13")), "6′10″", "SW", "West");
    Player JaeSean_Tate = new Player("JaeSean Tate", "HOU", "F", "8", calculateAge(format.parse("1995-10-28")), "6′4″", "SW", "West");
    Player Jalen_Green = new Player("Jalen Green", "HOU", "G", "4", calculateAge(format.parse("2002-02-09")), "6′6″", "SW", "West");
    Player Kenyon_Martin_Jr = new Player("Kenyon Martin Jr", "HOU", "F", "6", calculateAge(format.parse("2001-01-06")), "6′6″", "SW", "West");
    Player Kevin_Porter_Jr = new Player("Kevin Porter Jr", "HOU", "G", "3", calculateAge(format.parse("2000-05-04")), "6′4″", "SW", "West");
    ///HOU PLAYERS

    ///NOP PLAYERS
    Player Brandon_Ingram = new Player("Brandon Ingram", "NOP", "F", "14", calculateAge(format.parse("1997-09-02")), "6′8″", "SW", "West");
    Player CJ_McCollum = new Player("CJ McCollum", "NOP", "G", "3", calculateAge(format.parse("1991-09-19")), "6′3″", "SW", "West");
    Player Herbert_Jones = new Player("Herbert Jones", "NOP", "F", "5", calculateAge(format.parse("1998-10-06")), "6′8″", "SW", "West");
    Player Jonas_Valanciunas = new Player("Jonas Valanciunas", "NOP", "C", "17", calculateAge(format.parse("1992-05-06")), "6′11″", "SW", "West");
    Player Jose_Alvarado = new Player("Jose Alvarado", "NOP", "G", "15", calculateAge(format.parse("1998-04-12")), "6′0″", "SW", "West");
    Player Josh_Richardson = new Player("Josh Richardson", "NOP", "G", "2", calculateAge(format.parse("1993-09-15")), "6′5″", "SW", "West");
    Player Larry_Nance_Jr = new Player("Larry Nance Jr", "NOP", "F", "22", calculateAge(format.parse("1993-01-01")), "6′7″", "SW", "West");
    Player Trey_Murphy = new Player("Trey Murphy", "NOP", "F", "25", calculateAge(format.parse("2000-06-18")), "6′9″", "SW", "West");
    Player Zion_Williamson = new Player("Zion Williamson", "NOP", "F", "1", calculateAge(format.parse("2000-07-06")), "6′6″", "SW", "West");
    ///NOP PLAYERS

    ///SAS PLAYERS
    Player Devin_Vassell = new Player("Devin Vassell", "SAS", "G", "24", calculateAge(format.parse("2000-08-23")), "6′7″", "SW", "West");
    Player Devonte_Graham = new Player("Devonte Graham", "SAS", "G", "4", calculateAge(format.parse("1995-02-22")), "6′1″", "SW", "West");
    Player Gorgui_Dieng = new Player("Gorgui Dieng", "SAS", "C", "41", calculateAge(format.parse("1990-01-18")), "6′10″", "SW", "West");
    Player Jeremy_Sochan = new Player("Jeremy Sochan", "SAS", "F", "10", calculateAge(format.parse("2003-05-20")), "6′9″", "SW", "West");
    Player Keldon_Johnson = new Player("Keldon Johnson", "SAS", "F", "3", calculateAge(format.parse("1999-10-11")), "6′5″", "SW", "West");
    Player Tre_Jones = new Player("Tre Jones", "SAS", "G", "33", calculateAge(format.parse("2000-01-08")), "6′2″", "SW", "West");
    Player Zach_Collins = new Player("Zach Collins", "SAS", "F", "23", calculateAge(format.parse("1997-11-19")), "6′11″", "SW", "West");
    ///SAS PLAYERS

    ///SAC PLAYERS
    Player Davion_Mitchell = new Player("Davion Mitchell", "SAC", "G", "15", calculateAge(format.parse("1998-09-05")), "6′1″", "Pac.", "West");
    Player DeAaron_Fox = new Player("DeAaron Fox", "SAC", "G", "5", calculateAge(format.parse("1997-12-20")), "6′3″", "Pac.", "West");
    Player Domantas_Sabonis = new Player("Domantas Sabonis", "SAC", "C", "10", calculateAge(format.parse("1996-05-03")), "6′11″", "Pac.", "West");
    Player Harrison_Barnes = new Player("Harrison Barnes", "SAC", "F", "40", calculateAge(format.parse("1992-05-30")), "6′8″", "Pac.", "West");
    Player Keegan_Murray = new Player("Keegan Murray", "SAC", "F", "13", calculateAge(format.parse("2000-08-19")), "6′8″", "Pac.", "West");
    Player Kevin_Huerter = new Player("Kevin Huerter", "SAC", "F", "9", calculateAge(format.parse("1998-08-27")), "6′7″", "Pac.", "West");
    Player Malik_Monk = new Player("Malik Monk", "SAC", "G", "0", calculateAge(format.parse("1998-02-04")), "6′3″", "Pac.", "West");
    Player Richaun_Holmes = new Player("Richaun Holmes", "SAC", "F", "22", calculateAge(format.parse("1993-10-15")), "6′10″", "Pac.", "West");
    Player Trey_Lyles = new Player("Trey Lyles", "SAC", "F", "41", calculateAge(format.parse("1995-11-05")), "6′9″", "Pac.", "West");
    ///SAC PLAYERS

    ///DEN PLAYERS
    Player Aaron_Gordon = new Player("Aaron Gordon", "DEN", "F", "50", calculateAge(format.parse("1995-09-16")), "6′8″", "NW", "West");
    Player Bruce_Brown = new Player("Bruce Brown", "DEN", "G", "11", calculateAge(format.parse("1996-08-15")), "6′4″", "NW", "West");
    Player DeAndre_Jordan = new Player("DeAndre Jordan", "DEN", "C", "6", calculateAge(format.parse("1988-07-21")), "6′11″", "NW", "West");
    Player Jamal_Murray = new Player("Jamal Murray", "DEN", "G", "27", calculateAge(format.parse("1997-02-23")), "6′4″", "NW", "West");
    Player Jeff_Green = new Player("Jeff Green", "DEN", "F", "32", calculateAge(format.parse("1986-08-28")), "6′8″", "NW", "West");
    Player Kentavious_Caldwell_Pope = new Player("Kentavious Caldwell Pope", "DEN", "G", "5", calculateAge(format.parse("1993-02-12")), "6′5″", "NW", "West");
    Player Michael_Porter_Jr = new Player("Michael Porter Jr", "DEN", "F", "1", calculateAge(format.parse("1998-06-29")), "6′10″", "NW", "West");
    Player Nikola_Jokic = new Player("Nikola Jokic", "DEN", "C", "15", calculateAge(format.parse("1995-02-19")), "7′0″", "NW", "West");
    Player Reggie_Jackson = new Player("Reggie Jackson", "DEN", "G", "7", calculateAge(format.parse("1990-04-16")), "6′3″", "NW", "West");
    ///DEN PLAYERS

    ///MIN PLAYERS
    Player Anthony_Edwards = new Player("Anthony Edwards", "MIN", "G", "1", calculateAge(format.parse("2001-08-05")), "6′5″", "NW", "West");
    Player Austin_Rivers = new Player("Austin Rivers", "MIN", "G", "25", calculateAge(format.parse("1992-08-01")), "6′3″", "NW", "West");
    Player Jaden_McDaniels = new Player("Jaden McDaniels", "MIN", "F", "3", calculateAge(format.parse("2000-09-29")), "6′10″", "NW", "West");
    Player Jaylen_Nowell = new Player("Jaylen Nowell", "MIN", "G", "4", calculateAge(format.parse("1999-07-09")), "6′4″", "NW", "West");
    Player Karl_Anthony_Towns = new Player("Karl Anthony Towns", "MIN", "C", "32", calculateAge(format.parse("1995-11-15")), "6′11″", "NW", "West");
    Player Kyle_Anderson = new Player("Kyle Anderson", "MIN", "F", "5", calculateAge(format.parse("1993-09-20")), "6′9″", "NW", "West");
    Player Mike_Conley = new Player("Mike Conley", "MIN", "G", "10", calculateAge(format.parse("1987-10-11")), "6′1″", "NW", "West");
    Player Nickeil_Alexander_Walker = new Player("Nickeil Alexander Walker", "MIN", "G", "9", calculateAge(format.parse("1998-09-02")), "6′5″", "NW", "West");
    Player Rudy_Gobert = new Player("Rudy Gobert", "MIN", "C", "27", calculateAge(format.parse("1992-06-26")), "7′1″", "NW", "West");
    Player Taurean_Prince = new Player("Taurean Prince", "MIN", "F", "12", calculateAge(format.parse("1994-03-22")), "6′7″", "NW", "West");
    ///MIN PLAYERS

    ///POR PLAYERS
    Player Anfernee_Simons = new Player("Anfernee Simons", "POR", "G", "1", calculateAge(format.parse("1999-06-08")), "6′3″", "NW", "West");
    Player Cam_Reddish = new Player("Cam Reddish", "POR", "F", "5", calculateAge(format.parse("1999-09-01")), "6′8″", "NW", "West");
    Player Damian_Lillard = new Player("Damian Lillard", "POR", "G", "0", calculateAge(format.parse("1990-07-15")), "6′2″", "NW", "West");
    Player Jerami_Grant = new Player("Jerami Grant", "POR", "F", "9", calculateAge(format.parse("1994-03-12")), "6′8″", "NW", "West");
    Player Justise_Winslow = new Player("Justise Winslow", "POR", "F", "26", calculateAge(format.parse("1996-03-26")), "6′6″", "NW", "West");
    Player Jusuf_Nurkic = new Player("Jusuf Nurkic", "POR", "C", "27", calculateAge(format.parse("1994-08-23")), "7′0″", "NW", "West");
    Player Kevin_Knox_II = new Player("Kevin Knox II", "POR", "F", "11", calculateAge(format.parse("1999-08-11")), "6′7″", "NW", "West");
    Player Matisse_Thybulle = new Player("Matisse Thybulle", "POR", "G", "4", calculateAge(format.parse("1997-03-04")), "6′5″", "NW", "West");
    Player Shaedon_Sharpe = new Player("Shaedon Sharpe", "POR", "G", "17", calculateAge(format.parse("2003-05-30")), "6′6″", "NW", "West");
    ///POR PLAYERS

    ///OKC PLAYERS
    Player Aleksej_Pokusevski = new Player("Aleksej Pokusevski", "OKC", "F", "17", calculateAge(format.parse("2001-12-26")), "7′0″", "NW", "West");
    Player Dario_Saric = new Player("Dario Saric", "OKC", "F", "9", calculateAge(format.parse("1994-04-08")), "6′10″", "NW", "West");
    Player Jalen_Williams = new Player("Jalen Williams", "OKC", "G", "8", calculateAge(format.parse("2001-04-14")), "6′6″", "NW", "West");
    Player Josh_Giddey = new Player("Josh Giddey", "OKC", "G", "3", calculateAge(format.parse("2002-10-10")), "6′8″", "NW", "West");
    Player Luguentz_Dort = new Player("Luguentz Dort", "OKC", "G", "5", calculateAge(format.parse("1999-04-19")), "6′3″", "NW", "West");
    Player Shai_Gilgeous_Alexander = new Player("Shai Gilgeous Alexander", "OKC", "G", "2", calculateAge(format.parse("1998-07-12")), "6′5″", "NW", "West");
    Player Tre_Mann = new Player("Tre Mann", "OKC", "G", "23", calculateAge(format.parse("2001-02-03")), "6′5″", "NW", "West");
    ///OKC PLAYERS

    ///PHX PLAYERS
    Player Kevin_durant = new Player("Kevin Durant", "PHX", "F", "35", calculateAge(format.parse("1988-09-29")), "6′10″", "Pac.", "West");
    Player Bismack_Biyombo = new Player("Bismack Biyombo", "PHX", "C", "18", calculateAge(format.parse("1992-08-28")), "6′8″", "Pac.", "West");
    Player Cameron_Payne = new Player("Cameron Payne", "PHX", "G", "15", calculateAge(format.parse("1994-08-08")), "6′1″", "Pac.", "West");
    Player Chris_Paul = new Player("Chris Paul", "PHX", "G", "3", calculateAge(format.parse("1985-05-06")), "6′0″", "Pac.", "West");
    Player Damion_Lee = new Player("Damion Lee", "PHX", "G", "10", calculateAge(format.parse("1992-10-21")), "6′5″", "Pac.", "West");
    Player Deandre_Ayton = new Player("Deandre Ayton", "PHX", "C", "22", calculateAge(format.parse("1998-07-23")), "6′11″", "Pac.", "West");
    Player Devin_Booker = new Player("Devin Booker", "PHX", "G", "1", calculateAge(format.parse("1996-10-30")), "6′5″", "Pac.", "West");
    Player Landry_Shamet = new Player("Landry Shamet", "PHX", "G", "14", calculateAge(format.parse("1997-03-13")), "6′4″", "Pac.", "West");
    Player TJ_Warren = new Player("TJ Warren", "PHX", "F", "21", calculateAge(format.parse("1993-09-05")), "6′8″", "Pac.", "West");
    Player Terrence_Ross = new Player("Terrence Ross", "PHX", "G", "8", calculateAge(format.parse("1991-02-05")), "6′6″", "Pac.", "West");
    Player Torrey_Craig = new Player("Torrey Craig", "PHX", "F", "0", calculateAge(format.parse("1990-12-19")), "6′7″", "Pac.", "West");
    ///PHX PLAYERS


    Player[] Players = {
//
            ///BOS PLAYERS
            Al_Horford,
            Blake_Griffin,
            Derrick_White,
            Grant_Williams,
            Jaylen_Brown,
            Jayson_Tatum,
            Malcolm_Brogdon,
            Marcus_Smart,
            Payton_Pritchard,
            Robert_Williams_III,
            Sam_Hauser,
            ///BOS PLAYERS

            ///OKC PLAYERS
            Aleksej_Pokusevski,
            Dario_Saric,
            Jalen_Williams,
            Josh_Giddey,
            Luguentz_Dort,
            Shai_Gilgeous_Alexander,
            Tre_Mann,
            ///OKC PLAYERS

            ///POR PLAYERS
            Anfernee_Simons,
            Cam_Reddish,
            Damian_Lillard,
            Jerami_Grant,
            Justise_Winslow,
            Jusuf_Nurkic,
            Kevin_Knox_II,
            Matisse_Thybulle,
            Shaedon_Sharpe,
            ///POR PLAYERS

            ///MIN PLAYERS
            Anthony_Edwards,
            Austin_Rivers,
            Jaden_McDaniels,
            Jaylen_Nowell,
            Karl_Anthony_Towns,
            Kyle_Anderson,
            Mike_Conley,
            Nickeil_Alexander_Walker,
            Rudy_Gobert,
            Taurean_Prince,
            ///MIN PLAYERS

            ///DEN PLAYERS
            Aaron_Gordon,
            Bruce_Brown,
            DeAndre_Jordan,
            Jamal_Murray,
            Jeff_Green,
            Kentavious_Caldwell_Pope,
            Michael_Porter_Jr,
            Nikola_Jokic,
            Reggie_Jackson,
            ///DEN PLAYERS

            ///SAC PLAYERS
            Davion_Mitchell,
            DeAaron_Fox,
            Domantas_Sabonis,
            Harrison_Barnes,
            Keegan_Murray,
            Kevin_Huerter,
            Malik_Monk,
            Richaun_Holmes,
            Trey_Lyles,
            ///SAC PLAYERS

            ///SAS PLAYERS
            Devin_Vassell,
            Devonte_Graham,
            Gorgui_Dieng,
            Jeremy_Sochan,
            Keldon_Johnson,
            Tre_Jones,
            Zach_Collins,
            ///SAS PLAYERS

            ///NOP PLAYERS
            Brandon_Ingram,
            CJ_McCollum,
            Herbert_Jones,
            Jonas_Valanciunas,
            Jose_Alvarado,
            Josh_Richardson,
            Larry_Nance_Jr,
            Trey_Murphy,
            Zion_Williamson,
            ///NOP PLAYERS

            ///DAL PLAYERS
            Christian_Wood,
            Davis_Bertans,
            Dwight_Powell,
            Facundo_Campazzo,
            Frank_Ntilikina,
            JaVale_McGee,
            Josh_Green,
            Kyrie_Irving,
            Luka_Doncic,
            Markieff_Morris,
            Maxi_Kleber,
            Reggie_Bullock,
            Tim_Hardaway_Jr,
            ///DAL PLAYERS

            ///HOU PLAYERS
            Alperen_Sengun,
            Boban_Marjanovic,
            Frank_Kaminsky,
            Jabari_Smith,
            JaeSean_Tate,
            Jalen_Green,
            Kenyon_Martin_Jr,
            Kevin_Porter_Jr,
            ///HOU PLAYERS

            ///ORL PLAYERS
            Bol_Bol,
            Cole_Anthony,
            Franz_Wagner,
            Gary_Harris,
            Jalen_Suggs,
            Markelle_Fultz,
            Michael_Carter_Williams,
            Paolo_Banchero,
            Wendell_Carter_Jr,
            ///ORL PLAYERS

            ///MIA PLAYERS
            Bam_Adebayo,
            Caleb_Martin,
            Cody_Zeller,
            Duncan_Robinson,
            Gabe_Vincent,
            Jimmy_Butler,
            Kevin_Love,
            Kyle_Lowry,
            Max_Strus,
            Tyler_Herro,
            Victor_Oladipo,
            ///MIA PLAYERS

            ///CHA PLAYERS
            Dennis_Smith_Jr,
            Gordon_Hayward,
            Kelly_Oubre_Jr,
            LaMelo_Ball,
            PJ_Washington,
            Terry_Rozier,
            ///CHA PLAYERS

            ///LAC PLAYERS
            Mason_Plumlee,
            Bones_Hyland,
            Eric_Gordon,
            Ivica_Zubac,
            Kawhi_Leonard,
            Marcus_Morris_Sr,
            Nicolas_Batum,
            Norman_Powell,
            Paul_George,
            Robert_Covington,
            Russell_Westbrook,
            Terance_Mann,
            ///LAC PLAYERS

            /// ATL PLAYERS
            Bogdan_Bogdanovic,
            Clint_Capela,
            DeAndre_Hunter,
            Dejounte_Murray,
            John_Collins,
            Onyeka_Okongwu,
            Saddiq_Bey,
            Trae_Young,
            /// ATL PLAYERS

            /// MIL PLAYERS
            Bobby_Portis,
            Brook_Lopez,
            Giannis_Antetokounmpo,
            Goran_Dragic,
            Grayson_Allen,
            Jae_Crowder,
            Jevon_Carter,
            Joe_Ingles,
            Jrue_Holiday,
            Khris_Middleton,
            Pat_Connaughton,
            Wesley_Matthews,
            /// MIL PLAYERS

            ///CHI PLAYERS
            Alex_Caruso,
            Andre_Drummond,
            Ayo_Dosunmu,
            Coby_White,
            Demar_DeRozan,
            Derrick_Jones_Jr,
            Nikola_Vucevic,
            Patrick_Beverley,
            Patrick_Williams,
            Zach_LaVine,
            ///CHI PLAYERS

            ///DET PLAYERS
            Alec_Burks,
            Bojan_Bogdanovic,
            Cade_Cunningham,
            Isaiah_Stewart,
            Jaden_Ivey,
            James_Wiseman,
            Killian_Hayes,
            Marvin_Bagley_III,
            Jalen_Duren,
            /// DET PLAYERS

            ///IND PLAYERS
            Bennedict_Mathurin,
            Buddy_Hield,
            Chris_Duarte,
            Daniel_Theis,
            George_Hill,
            Isaiah_Jackson,
            Jalen_Smith,
            Myles_Turner,
            TJ_McConnell,
            Tyrese_Haliburton,
            ///IND PLAYERS

            ///CLE PLAYERS
            Caris_LeVert,
            Cedi_Osman,
            Darius_Garland,
            Donovan_Mitchell,
            Evan_Mobley,
            Isaac_Okoro,
            Jarrett_Allen,
            Ricky_Rubio,
            Robin_Lopez,
            ///CLE PLAYERS

            ///TOR PLAYERS
            Chris_Boucher,
            Juancho_Hernangomez,
            Fred_VanVleet,
            Gary_Trent_Jr,
            Jakob_Poeltl,
            OG_Anunoby,
            Thaddeus_Young,
            Scottie_Barnes,
            Precious_Achiuwa,
            Pascal_Siakam,
            Otto_Porter_Jr,
            ///TOR PLAYERS

            /// NYK PLAYERS
            Derrick_Rose,
            Evan_Fournier,
            Immanuel_Quickley,
            Jalen_Brunson,
            Jericho_Sims,
            Josh_Hart,
            Julius_Randle,
            Mitchell_Robinson,
            Obi_Toppin,
            Quentin_Grimes,
            RJ_Barrett,
            /// NYK PLAYERS

            /// PHI PLAYERS
            Danuel_House_Jr,
            DeAnthony_Melton,
            Georges_Niang,
            James_Harden,
            Joel_Embiid,
            Montrezl_Harrell,
            PJ_Tucker,
            Shake_Milton,
            Tobias_Harris,
            Tyrese_Maxey,
            /// PHI PLAYERS

            ///BKN PLAYERS
            Ben_Simmons,
            Cam_Thomas,
            Cameron_Johnson,
            Dorian_Finney_Smith,
            Joe_Harris,
            Mikal_Bridges,
            Nicolas_Claxton,
            Patty_Mills,
            Royce_Oneale,
            Seth_Curry,
            Spencer_Dinwiddie,
            Yuta_Watanabe,
            ///BKN PLAYERS

            ///GSW PLAYERS
            Steph_Curry,
            Klay_Thompson,
            Andrew_Wiggins,
            Draymond_Green,
            Kevon_Looney,
            Jordan_Poole,
            Andre_Iguodala,
            Donte_DiVincenzo,
            Gary_Payton_II,
            Moses_Moody,
            Jonathan_Kuminga,
            ///GSW PLAYERS

            /// MEM PLAYERS
            Steven_Adams,
            Desmond_Bane,
            Dillon_Brooks,
            Brandon_Clarke,
            Tyus_Jones,
            Jaren_Jackson_Jr,
            Luke_Kennard,
            Ja_Morant,
            Xavier_Tillman,
            /// MEM PLAYERS

            /// WAS PLAYERS
            Deni_Avdija,
            Bradley_Beal,
            Kendrick_Nunn,
            Kristaps_Porzingis,
            Kyle_Kuzma,
            Monte_Morris,
            Will_Barton,
            /// WAS PLAYERS

            /// UTA PLAYERS
            Collin_Sexton,
            Juan_Toscano_Anderson,
            Jordan_Clarkson,
            Kelly_Olynyk,
            Lauri_Markkanen,
            Rudy_Gay,
            Kris_Dunn,
            Talen_Horton_Tucker,
            Walker_Kessler,
            /// UTA PLAYERS

            /// LAL PLAYERS
            LeBron_James,
            Anthony_Davis,
            Austin_Reaves,
            Dangelo_Russell,
            Dennis_Schroder,
            Jarred_Vanderbilt,
            Lonnie_Walker_IV,
            Malik_Beasley,
            Mo_Bamba,
            Rui_Hachimura,
            /// LAL PLAYERS

            /// PHX PLAYERS
            Kevin_durant,
            Bismack_Biyombo,
            Cameron_Payne,
            Chris_Paul,
            Damion_Lee,
            Deandre_Ayton,
            Devin_Booker,
            Landry_Shamet,
            TJ_Warren,
            Terrence_Ross,
            Torrey_Craig
            /// PHX PLAYERS
    };

    private List<Player> guessList;

    public static RecyclerView recyclerView;

    public static GuessAdapter guessAdapter;

    public static TextView Hint;
    TextView tutor;

    private static TextView statsTextView;
    private static int numGamesPlayed;
    private static int numGamesWon;
    private static double winPercentage;
    private static int curStreak;
    private static int topStreak;
    private static int guessCount;

    public MainActivity() throws ParseException {
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        setContentView(R.layout.home_layout);


        sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        statsTextView = findViewById(R.id.firstTextView);
        numGamesPlayed = sharedPreferences.getInt("numGamesPlayed", 0);
        numGamesWon = sharedPreferences.getInt("numGamesWon", 0);
        curStreak = sharedPreferences.getInt("curStreak", 0);
        topStreak = sharedPreferences.getInt("topStreak", 0);
        guessCount = sharedPreferences.getInt("GuessCount" + guessNum, 0) + 1;
        winPercentage = (double) numGamesWon / numGamesPlayed * 100.0;


//        statsTextView.setPaintFlags(statsTextView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        statsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showStatsDialog();
            }
        });


        Hint = findViewById(R.id.HintTextView);
        tutor = findViewById(R.id.secondTextView);
//        tutor.setPaintFlags(tutor.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        Hint.setPaintFlags(tutor.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        Hint.setOnClickListener(v -> {
            if (Hint.getText().toString().equals("SHOW SILHOUETTE")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_layout, null);
                ImageView imageView = dialogView.findViewById(R.id.imageView);
                imageView.setImageDrawable(MainActivity.this.getDrawable(MainActivity.this.getResources().getIdentifier(toImage(player.getPlayerName()).toLowerCase(), "drawable", MainActivity.this.getPackageName())));
                imageView.setAdjustViewBounds(true);
                imageView.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
                Button closeButton = dialogView.findViewById(R.id.closeButton);

                builder.setView(dialogView);
                AlertDialog alertDialog = builder.create();

                closeButton.setOnClickListener(v1 -> alertDialog.dismiss());
                alertDialog.show();
            } else {
                View customDialogView = LayoutInflater.from(MainActivity.this).inflate(costum_alert_dialog, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(customDialogView);
                ImageView alertImage = customDialogView.findViewById(R.id.alert_image);
                alertImage.setImageDrawable(MainActivity.this.getDrawable(MainActivity.this.getResources().getIdentifier(toImage(player.getPlayerName().toLowerCase()), "drawable", MainActivity.this.getPackageName())));
                alertImage.setAdjustViewBounds(true);
                TextView alertText = customDialogView.findViewById(R.id.alert_text);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                if (win)
                    alertText.setText("Good Job, You Won!\nThe Player Was " + player.getPlayerName());
                else
                    alertText.setText("You Lost!\nThe Player Was " + player.getPlayerName());
            }
        });

        tutor.setOnClickListener(v -> {
            ;
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            TextView title = new TextView(MainActivity.this);
            title.setTextColor(Color.parseColor("#FFFFFF"));
            title.setText("GUESS THE MYSTERY PLAYER!");
            title.setBackgroundColor(Color.parseColor("#3C486B"));
            title.setPadding(30, 30, 30, 30);
            title.setGravity(Gravity.CENTER);
            title.setTextSize(20);
            builder.setCustomTitle(title);
            StringBuilder contentBuilder = new StringBuilder();
            contentBuilder.append("\n• You get eight guesses, try any current NBA player!\n\n");
            contentBuilder.append("• Green in any column indicates a match!\n\n");
            contentBuilder.append("• Yellow in the position column indicates a partial match to the mystery player's position.\n\n");
            contentBuilder.append("• Yellow in any other column indicates this attribute is within 2 (inches, years, numbers) of the mystery player.\n\n");
            contentBuilder.append("• If you get stuck, try enabling silhouette mode!");
            builder.setMessage(contentBuilder.toString());

            TextView openImageViewTextView = new TextView(MainActivity.this);
            openImageViewTextView.setText("Click here to view all the divisions");
            openImageViewTextView.setPaintFlags(openImageViewTextView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            openImageViewTextView.setGravity(Gravity.CENTER);
            openImageViewTextView.setPadding(30, 30, 30, 30);
            openImageViewTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Create a new dialog with an ImageView
                    AlertDialog.Builder secondDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                    ImageView imageView = new ImageView(MainActivity.this);
                    imageView.setImageResource(R.drawable.sw1);
                    secondDialogBuilder.setView(imageView);
                    AlertDialog secondDialog = secondDialogBuilder.create();
                    Window win = secondDialog.getWindow();
                    if (win != null) {
                        win.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#292828")));
                    }
                    LayoutInflater inflater = getLayoutInflater();
                    View closeButtonLayout = inflater.inflate(R.layout.dialog_close_button, null);
                    Button closeButton = closeButtonLayout.findViewById(R.id.close_button);
                    closeButton.setBackgroundColor(Color.parseColor("#3C486B"));
                    closeButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            secondDialog.dismiss();
                        }
                    });
                    secondDialog.show();
                }
            });
            builder.setNeutralButton("", null);
            builder.setCustomTitle(title)
                    .setPositiveButton("", null)
                    .setView(openImageViewTextView);
            AlertDialog dialog = builder.create();
            Window window = dialog.getWindow();
            if (window != null) {
                window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));
            }
            dialog.show();
        });


        cons = (ConstraintLayout) findViewById(R.id.linearLayout);

        player = Players[(int) (Math.random() * Players.length)];
        Log.d("banana", player.getPlayerName());
        guessList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        guessAdapter = new GuessAdapter(guessList, this);
        recyclerView.setAdapter(guessAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        guessButton = findViewById(R.id.guessButton);

        PlayerGuessed = findViewById(R.id.guessEditText);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, PlayerNames);
        PlayerGuessed.setAdapter(adapter);

        PlayerGuessed.setOnEditorActionListener((v, actionId, event) -> {
            String input = v.getText().toString().toLowerCase();
            adapter.getFilter().filter(input);
            return true;
        });

        PlayerGuessed.setOnItemClickListener((parent, view, position, id) -> {
            if (guessNum <= 8) {
                String guess = PlayerGuessed.getText().toString();
                if (checkValid(PlayerGuessed, Players, PlayersGuessed)) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(PlayerGuessed.getWindowToken(), 0);
                    PlayersGuessed[guessNum] = PlayerGuessed.getText().toString();
                    guessList.add(toPlayer(guess, Players));
                    guessAdapter.notifyItemInserted(guessNum);
                    adapter.notifyDataSetChanged();
                    recyclerView.smoothScrollToPosition(guessNum);
                    PlayerGuessed.setText("");
                    guessNum++;
                }

            }


        });

        PlayerGuessed.setHint("Guess 1 of 8");


        // TODO    add score and stats(win rate),

        guessButton.setOnClickListener(v -> {
            if (guessNum < 8 && guessButton.getText().toString().equals("GUESS")) {
            }

            if (guessButton.getText().toString().equals("RESTART")) {
                new AlertDialog.Builder(v.getContext())
                        .setTitle("Restart Confirmation")
                        .setMessage("Are you sure you want to restart?")
                        .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                            finish();
                            startActivity(getIntent());
                            guessNum = 0;
                            win = false;
                            re = 0;
//                                    resetGameStats();
                            qe = 0;
                            Hint.setText("SHOW SILHOUETTE");
                            overridePendingTransition(0, 0);


                        })
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }

    public Player toPlayer(String s, Player[] players) {
        for (int i = 0; i < players.length; i++) {
            if (s.equals(players[i].getPlayerName()))
                return players[i];
        }
        return null;
    }

    public boolean isIn(String Player, String[] players) {
        for (int i = 0; i < players.length; i++) {
            if (Player.equals(players[i]))
                return true;
        }
        return false;
    }

    public boolean isIn(String Player, Player[] players) {
        for (int i = 0; i < players.length; i++) {
            if (Player.equals(players[i].getPlayerName()))
                return true;
        }
        return false;
    }

    public boolean checkValid(AutoCompleteTextView p, Player[] ps, String[] psg) {

        if (TextUtils.isEmpty(p.getText().toString())) {
            Snackbar.make(cons, "No Player Was Guessed", Snackbar.LENGTH_LONG).show();
            return false;
        }
        if (isIn(p.getText().toString(), psg) && guessNum > 0) {
            Snackbar.make(cons, "Player Was Already Guessed", Snackbar.LENGTH_LONG).show();
            return false;
        }
        if (!isIn(p.getText().toString(), ps)) {
            Snackbar.make(cons, "Not A Player", Snackbar.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public static String toImage(String name) {
        return name.replaceAll("\\s+", "_");
    }

    public static boolean areHeightsWithinTwoInches(String height1, String height2) {
        int inches1 = convertHeightToInches(height1);
        int inches2 = convertHeightToInches(height2);
        return Math.abs(inches1 - inches2) <= 2;
    }

    public static boolean isOneHeightGreater(String height1, String height2) {
        int inches1 = convertHeightToInches(height1);
        int inches2 = convertHeightToInches(height2);
        return inches1 > inches2;
    }

    public static boolean areAgesWithinTwo(String age1, String age2) {
        int intAge1 = ageToInteger(age1);
        int intAge2 = ageToInteger(age2);
        return Math.abs(intAge1 - intAge2) <= 2;
    }

    private static int ageToInteger(String age) {
        if (age.equals("00")) {
            return 0;
        }
        return Integer.parseInt(age);
    }

    public static boolean isShorterStringInLongerString(String str1, String str2) {
        String shorterString = str1.length() <= str2.length() ? str1 : str2;
        String longerString = str1.length() > str2.length() ? str1 : str2;
        return longerString.contains(shorterString);
    }


    public static boolean isOneAgeGreater(String age1, String age2) {
        int intAge1 = ageToInteger(age1);
        int intAge2 = ageToInteger(age2);
        return intAge1 > intAge2;
    }

    private static int convertHeightToInches(String height) {
        String[] parts = height.split("′");
        int feet = Integer.parseInt(parts[0]);
        int inches = Integer.parseInt(parts[1].replace("″", ""));
        return feet * 12 + inches;
    }

    public int calculateAge(Date birthdate) {
        Calendar today = Calendar.getInstance();
        Calendar dob = Calendar.getInstance();
        dob.setTime(birthdate);
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (today.get(Calendar.MONTH) < dob.get(Calendar.MONTH)) {
            age--;
        } else if (today.get(Calendar.MONTH) == dob.get(Calendar.MONTH)
                && today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)) {
            age--;
        }
        return age;
    }

    public static void updateStatsTextView() {
        String statsText = String.format("Games Played: %d\nGames Won: %d\nWin Percentage: %.2f%%", numGamesPlayed, numGamesWon, winPercentage);
    }

    public static void updateGameStats(boolean userWon) {
        numGamesPlayed++;
        if (userWon) {
            numGamesWon++;
            curStreak++;
            if (curStreak > topStreak) {
                topStreak = curStreak;
            }
        } else {
            curStreak = 0;
        }
        winPercentage = (double) numGamesWon / numGamesPlayed * 100.0;

        if (topStreak > numGamesWon) {
            topStreak = numGamesWon;
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("numGamesPlayed", numGamesPlayed);
        editor.putInt("numGamesWon", numGamesWon);
        editor.putInt("curStreak", curStreak);
        editor.putInt("topStreak", topStreak);
        editor.putInt("GuessCount" + guessNum, guessCount);
        editor.putInt("TotalGuesses", sharedPreferences.getInt("TotalGuesses", 0) + 1);
        editor.apply();
    }


    private void showStatsDialog() {
        String statsText = String.format("\nGames Played: %d\n\nGames Won: %d\n\nWin Percentage: %.2f%%\n\nCurrent Streak: %d\n\nTop Streak: %d\n",
                numGamesPlayed, numGamesWon, winPercentage, curStreak, topStreak);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        TextView title = new TextView(MainActivity.this);
        title.setTextColor(Color.parseColor("#FFFFFF"));
        title.setText("YOUR STATS!");
        title.setBackgroundColor(Color.parseColor("#3C486B"));
        title.setPadding(30, 30, 30, 30);
        title.setGravity(Gravity.CENTER);
        title.setTextSize(20);
        builder.setCustomTitle(title);
        builder.setMessage(statsText);

        // Add ImageView to display the chart
        ImageView chartImageView = new ImageView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 20, 0, 0);
        chartImageView.setLayoutParams(layoutParams);

        // Retrieve and decode the chart bitmap from the statsText
        String base64Chart = statsText.substring(statsText.lastIndexOf("\n") + 1);
        byte[] decodedString = Base64.decode(base64Chart, Base64.DEFAULT);
        Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        chartImageView.setImageBitmap(decodedBitmap);

        LinearLayout dialogLayout = new LinearLayout(this);
        dialogLayout.setOrientation(LinearLayout.VERTICAL);
        dialogLayout.addView(chartImageView);

        builder.setView(dialogLayout);

        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());

        builder.show();
    }




    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("numGamesPlayed", numGamesPlayed);
        editor.putInt("numGamesWon", numGamesWon);
        editor.putInt("curStreak", curStreak);
        editor.putInt("topStreak", topStreak);
        editor.apply();
    }
}