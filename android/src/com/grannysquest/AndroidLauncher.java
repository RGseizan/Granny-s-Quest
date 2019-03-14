package com.grannysquest;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.grannysquest.constante.ConstanteDataBase;
import com.grannysquest.managers.database.UserDataBase;
import com.grannysquest.managers.database.UserDataBaseAndroid;
import com.grannysquest.objet.User;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		new ConstanteDataBase();
		UserDataBase udb = new UserDataBaseAndroid(this.getBaseContext(), ConstanteDataBase.USER_TABLE_NAME, ConstanteDataBase.USER_TABLE_VERSION);
		initialize(new App(udb), config);
	}
}