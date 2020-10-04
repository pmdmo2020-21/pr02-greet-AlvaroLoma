package es.iessaladillo.pedrojoya.greet;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import es.iessaladillo.pedrojoya.greet.databinding.MainActivityBinding;

public class MainActivity extends AppCompatActivity {
    private int contador;
    private boolean premiun;
    private MainActivityBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupViews();
    }

    private void setupViews() {
        contador=0;
        premiun=false;
        binding.progressBar.setMax(10);
        binding.botones.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                cambiarIcono();
            }
        });
        binding.greet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });
    }

    private void cambiarIcono() {
        int seletion=binding.botones.getCheckedRadioButtonId();


            if(seletion==2131165263){
                binding.icono.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_mr,0);
            }
            else if(seletion==2131165264){
                binding.icono.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_mrs,0);
            }
            else if(seletion==2131165265){
                binding.icono.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_ms,0);
            }

    }


    private void check() {


       if(binding.nameValue.getText().length()>0 || binding.sirNameValue.getText().length()>0){
           if(binding.premiun.isChecked()){
               //todo lo que quiera
               if(!premiun){
                   binding.progressBar.setVisibility(View.INVISIBLE);
                   binding.progressBar.setProgress(0);
                   binding.textProgres.setVisibility(View.INVISIBLE);
                   contador=0;
               }
               premiun=true;
               sayHi();

           }else{

               if(contador<10){
                   if(premiun){
                       binding.progressBar.setVisibility(View.VISIBLE);
                       binding.textProgres.setVisibility(View.VISIBLE);
                   }
                   premiun=false;
                   sayHi();
                   contador++;
                   binding.progressBar.incrementProgressBy(1);
                   binding.textProgres.setText(contador+" de 10");
               }else{
                   binding.textFinal.setText("buy premiun subscription to go on greeting ");
                    premiun=false;
               }


           }
       }




    }

    private void sayHi() {
        Editable nombre= binding.nameValue.getText();
        Editable apellido= binding.sirNameValue.getText();
        String title="";
        int seletion=binding.botones.getCheckedRadioButtonId();

        if(binding.checkBox.isChecked()){
            if(seletion==2131165263){
                title="Mr";
            }
            else if(seletion==2131165264){
                title="Mrs";
            }
            else if(seletion==2131165265){
                title="Ms";
            }
            binding.textFinal.setText("Good morning "+title+" "+nombre.toString()+" "+apellido.toString()+" Pleased to meet you ");
            //saludo educado
        }else{
            binding.textFinal.setText("Hello "+nombre.toString()+" "+apellido.toString()+" whats up? ");//saludo normal
        }


    }


}