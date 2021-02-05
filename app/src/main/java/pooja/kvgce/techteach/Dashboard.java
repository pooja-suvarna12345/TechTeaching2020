package pooja.kvgce.techteach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }
    public void Notes(View v)
    {
        Intent in=new Intent(this,Notes.class);
        startActivity(in);
    }
    public void Board(View v)
    {
        Intent in=new Intent(this,WhiteBoard.class);
        startActivity(in);
    }
    public void Attendance(View v)
    {
        Intent in=new Intent(this,Attendance.class);
        startActivity(in);
    }
    public void IaMark(View v)
    {
        Intent in=new Intent(this,IaMark.class);
        startActivity(in);
    }


}