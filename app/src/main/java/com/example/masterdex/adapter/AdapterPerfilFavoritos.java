package com.example.masterdex.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.example.masterdex.R;
import com.example.masterdex.interfaces.PokemonListener;
import com.example.masterdex.models.Pokemon;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class AdapterPerfilFavoritos extends RecyclerView.Adapter<AdapterPerfilFavoritos.ViewHolder> {

    private List<Pokemon> favoritos;
    private PokemonListener pokemonListener;

    public AdapterPerfilFavoritos(PokemonListener pokemonListener,List<Pokemon> favoritos, FragmentActivity activity) {
        this.pokemonListener = pokemonListener;
        this.favoritos = new ArrayList<>(favoritos);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pokemons_celula_favoritos, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        final Pokemon pokemon = favoritos.get(position);
        String pok = pokemon.getName();
        pok = pok.substring(0, 1).toUpperCase().concat(pok.substring(1));
        viewHolder.textNomePokemon.setText(pok);
        Picasso.get().load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + pokemon.getNumber() + ".png")
                .into(viewHolder.imageFotoPokemon);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pokemonListener.onPokemonClicado(pokemon);
            }
        });
    }

    @Override
    public int getItemCount() {
        return favoritos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textNomePokemon;
        private ImageView imageFotoPokemon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textNomePokemon = itemView.findViewById(R.id.textNomePokemon);
            imageFotoPokemon = itemView.findViewById(R.id.imageFotoPokemon);
        }
    }
}