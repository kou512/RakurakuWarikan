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
		// グローバル変数取得
		globals = (Globals) this.getApplication();

		Button btnCostApply = (Button) findViewById(R.id.btnCostApply);
		btnCostApply.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				EditText et_totalCost = (EditText) findViewById(R.id.et_totalCost);
				globals.totalCost = Integer.parseInt(et_totalCost.getText()
						.toString());
				makeToast("合計金額を" + et_totalCost.getText().toString() + "円に設定しました。");
			}
		});

		
	}

	//トースト生成メソッド
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
