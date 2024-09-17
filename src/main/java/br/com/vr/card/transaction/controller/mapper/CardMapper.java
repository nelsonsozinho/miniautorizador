package br.com.vr.card.transaction.controller.mapper;
import br.com.vr.card.transaction.controller.rest.CardRest;
import br.com.vr.card.transaction.model.Card;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardMapper {

    CardRest toResponse(Card card);

    Card toCard(CardRest card);

}