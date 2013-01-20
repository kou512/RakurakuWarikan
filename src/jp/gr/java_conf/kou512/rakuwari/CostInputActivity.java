package jp.gr.java_conf.kou512.rakuwari;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CostInputActivity extends Activity {
	Globals globals;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cost_input);
		// �O���[�o���ϐ��擾
		globals = (Globals) this.getApplication();

		Button btnCostApply = (Button) findViewById(R.id.btnCostApply);
		btnCostApply.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				EditText et_totalCost = (EditText) findViewById(R.id.et_totalCost);
				globals.totalCost = Integer.parseInt(et_totalCost.getText()
						.toString());
				makeToast("���v���z��" + et_totalCost.getText().toString() + "�~�ɐݒ肵�܂����B");
			}
		});

		
	}

	//�g�[�X�g�������\�b�h
	private void makeToast(String str) {
		Toast.makeText(this,str,Toast.LENGTH_LONG).show();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_cost_input, menu);
		return true;
	}

}
