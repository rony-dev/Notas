package com.notas;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.ContentResolver;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Tela de login do aplicativo
 */
public class LoginActivity extends Activity {

    /**
     *  Constante que armazena todos os usuários permitidos a utilizar o aplicativo.
     *  O formato segue o padrão "<usuario>:<senha>"
     */
    private static final String[] CREDENCIAIS_BASICAS = new String[]{
        "admin:asdf"
    };

    // Referências aos controles da UI
    private EditText txtUsuario;
    private EditText txtSenha;
    private View viewLogin;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Mapeia os controles da UI
        txtUsuario = (EditText) findViewById(R.id.txt_usuario);
        txtSenha = (EditText) findViewById(R.id.txt_senha);
        buttonLogin = (Button) findViewById(R.id.button_login);

        buttonLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        viewLogin = findViewById(R.id.view_login);
    }

    /**
     * Tenta fazer login utilizando os dados inseridos na view
     */
    public void attemptLogin() {
        // Limpa os erros
        txtUsuario.setError(null);
        txtSenha.setError(null);

        // Recupera os dados digitados nos campos de login e senha
        String usuario = txtUsuario.getText().toString();
        String senha = txtSenha.getText().toString();

        boolean formValido = false;
        View campoFocado = null;

        // Valida se a senha digitada é válida
        if (!validarSenha(senha)) {
            txtSenha.setError(getString(R.string.erro_senha_invalida));
            campoFocado = txtSenha;
            formValido = true;
        }

        // Valida se o usuario digitado é válido
        if (!validarUsuario(usuario)) {
            txtUsuario.setError(getString(R.string.erro_usuario_invalido));
            campoFocado = txtUsuario;
            formValido = true;
        }

        if (formValido) {
            // Se houver erro, coloca o foco no campo com o erro
            campoFocado.requestFocus();
        } else {
            // Mostra o spinner de carregamento e tenta fazer login
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Método de validação do usuário
     */
    private boolean validarUsuario(String usuario) {
        boolean resultado = true;

        if(TextUtils.isEmpty(usuario))
            resultado = false;

        if(usuario.length() < 4)
            resultado = false;

        return resultado;
    }

    /**
     * Método de validação da senha
     */
    private boolean validarSenha(String senha) {
        boolean resultado = true;

        if(TextUtils.isEmpty(senha))
            resultado = false;

        if(senha.length() < 4)
            resultado = false;

        return resultado;
    }
}