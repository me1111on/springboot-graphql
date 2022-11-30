package com.example.DemoGraphQL;

import com.example.DemoGraphQL.exception.GraphQLErrorAdapter;
import com.example.DemoGraphQL.model.City;
import com.example.DemoGraphQL.model.Trip;
import com.example.DemoGraphQL.repository.CityRepository;
import com.example.DemoGraphQL.repository.TripRepository;
import com.example.DemoGraphQL.resolver.Mutation;
import com.example.DemoGraphQL.resolver.Query;
import com.example.DemoGraphQL.resolver.TripResolver;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.kickstart.execution.error.GraphQLErrorHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class DemoGraphQlApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DemoGraphQlApplication.class, args);
	}

	@Bean
	public GraphQLErrorHandler errorHandler() {
		return new GraphQLErrorHandler() {
			@Override
			public List<GraphQLError> processErrors(List<GraphQLError> errors) {
				List<GraphQLError> clientErrors = errors.stream()
						.filter(this::isClientError)
						.collect(Collectors.toList());

				List<GraphQLError> serverErrors = errors.stream()
						.filter(e -> !isClientError(e))
						.map(GraphQLErrorAdapter::new)
						.collect(Collectors.toList());

				List<GraphQLError> e = new ArrayList<>();
				e.addAll(clientErrors);
				e.addAll(serverErrors);
				return e;
			}

			protected boolean isClientError(GraphQLError error) {
				return !(error instanceof ExceptionWhileDataFetching || error instanceof Throwable);
			}
		};
	}

	@Bean
	public TripResolver cityResolver(CityRepository cityRepository){
		return new TripResolver(cityRepository);
	}

	@Bean
	public Query query( CityRepository cityRepository, TripRepository tripRepository) {
		return new Query(cityRepository, tripRepository);
	}

	@Bean
	public Mutation mutation(CityRepository cityRepository, TripRepository tripRepository) {
		return new Mutation(cityRepository, tripRepository);
	}

	@Bean
	public CommandLineRunner demo(CityRepository cityRepository, TripRepository tripRepository) {
		return (args) -> {
			cityRepository.save(new City("Seoul","서울은 대한민국의 수도입니다.", OffsetDateTime.now(), OffsetDateTime.now()));
			cityRepository.save(new City("Tokyo","도쿄는 일본의 수도입니다.", OffsetDateTime.now(), OffsetDateTime.now()));
			cityRepository.save(new City("Beijing","베이징은 중국의 수도입니다.", OffsetDateTime.now(), OffsetDateTime.now()));
		};
	}
}
