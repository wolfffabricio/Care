package com.example.gocode.care.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.gocode.care.R;
import com.example.gocode.care.adapters.TabsAdapterMain;
import com.example.gocode.care.models.Usuario;
import com.example.gocode.care.tabs.SlidingTabLayout;

import java.util.ArrayList;

public class FeedActivityMain extends AppCompatActivity {

    private Toolbar mToolbar;
    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_main);

        //TOOLBAR
        mToolbar = (Toolbar) findViewById(R.id.tbFeedMain);
        mToolbar.setTitle("Care");
        setSupportActionBar(mToolbar);

        //TABS
        mViewPager = (ViewPager) findViewById(R.id.vpTabsMain);
        mViewPager.setAdapter(new TabsAdapterMain(getSupportFragmentManager(), this));
        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.stlTabsMain);
        mSlidingTabLayout.setDistributeEvenly(true);
        mSlidingTabLayout.setCustomTabView(R.layout.tab_view, R.id.tvTab);
        mSlidingTabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mSlidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.colorAccent));
        mSlidingTabLayout.setViewPager(mViewPager);
    }

    //MENUS ACTION BAR
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_feed_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView;
        MenuItem item = menu.findItem(R.id.action_main_activity);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            searchView = (SearchView) item.getActionView();
        } else {
            searchView = (SearchView) MenuItemCompat.getActionView(item);
        }

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint(getResources().getText(R.string.search_hint));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_main_settings) {
            Intent i = new Intent(FeedActivityMain.this, MainConfiguracaoActivity.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //BANCO DE DADOS
    public ArrayList<Usuario> getSetUserList(int qtd) {
        String[] nomes = new String[]{"Ana Rosa", "Eduarda Souza", "Antônio Duarte", "Roberta Silva", "Care", "Bruna Chaves"};
        String[] descricao = new String[]{
                "Cuidadora de crianças",
                "Cuidadora de idosos",
                "Cuidador auxiliar de pessoas com deficiência física",
                "Cuidadora de crianças",
                "Criadores do APP.",
                "Cuidadora de idosos"};

        String[] cidade = new String[]{"Porto Alegre", "Santa Maria", "Canoas", "Viamão", "Porto Alegre", "Uruguaiana"};
        String[] estado = new String[]{"RS"};
        int[] fotos = new int[]{R.drawable.cuidador1, R.drawable.cuidador2, R.drawable.cuidador3, R.drawable.cuidador4, R.drawable.onlinelogomaker_081716_2253, R.drawable.cuidador5};

        String[] experiencia = new String[]{
                "Técnica em recreação infantil e especialista na arte de cuidar de crianças de até 10 anos. " +
                        "Atualmente estou disponível para trabalhar aos finais de semana. Surgiu um imprevisto? Ligue Agora mesmo!",

                "Recém-formada em psicologia, já atuei algumas vezes como cuidadora de idosos. Ligue para mais informações.",

                "Formado em enfermagem pela UFRGS  e fluente em Língua Brasileira de Sinais(LIBRAS), atuo na área de cuidados há seis anos. " +
                        "Tenho experiência com tetraplégicos e deficientes auditivos. Entre em contato!",

                "Ex-recreadora infantil e estudante de pedagogia do 1° semestre, trabalho com bebês e crianças de até 6 anos de idade. " +
                        "Se você é de Viamão e procura alguém confiável para tomar conta de seus filhos, ligue o quanto antes! Obrigada.",

                "Somos um grupo com o objetivo de levar cuidados com mais facilidade as pessoas.",

                "Sou técnica em enfermagem e estou atuando como cuidadora de idosos desde 2013. Estou disponível para atuar em tempo integral" +
                        " por um preço justo. Maiores informações por telefone."};

        String[] email = new String[]{"anarosa04@hotmail.com", "eduarda.souza@gmail.com", "duarte.200@gmail.com", "berta_chaves@yahoo.com", "developers@care.com", "bruna_chaves@hotmail.com"};
        String[] telefone = new String[]{"34967406", "81244450", "96902022", "30220042", "80206745", "98975604"};
        ArrayList<Usuario> lisAux = new ArrayList<>();

        for (int i = 0; i < qtd; i++) {
            Usuario u = new Usuario(nomes[i % nomes.length], cidade[i % cidade.length], estado[i % estado.length], fotos[i % fotos.length], descricao[i % descricao.length], email[i % email.length], telefone[i % telefone.length], experiencia[i % experiencia.length]);
            lisAux.add(u);
        }
        return lisAux;
    }
}