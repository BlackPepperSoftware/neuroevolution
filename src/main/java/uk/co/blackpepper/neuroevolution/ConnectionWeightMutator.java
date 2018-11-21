package uk.co.blackpepper.neuroevolution;

import java.util.Random;
import java.util.stream.Stream;

public class ConnectionWeightMutator implements Mutator {
	
	private static final double CONNECTION_WEIGHT_MUTATION_RATE = 0.25;
	
	private static final double CONNECTION_WEIGHT_MUTATION_STEP = 0.1;
	
	private final Random random;
	
	public ConnectionWeightMutator(Random random) {
		this.random = random;
	}
	
	@Override
	public Genome mutate(Genome genome) {
		Genome result = genome.copy();
		
		if (random.nextDouble() < CONNECTION_WEIGHT_MUTATION_RATE) {
			result = mutateConnectionWeights(result);
		}
		
		return result;
	}
	
	Genome mutateConnectionWeights(Genome genome) {
		Stream<ConnectionGene> connections = genome.getConnectionGenes()
			.map(this::mutateConnectionWeight);
		
		return new Genome(Stream.concat(genome.getNodeGenes(), connections));
	}
	
	ConnectionGene mutateConnectionWeight(ConnectionGene connection) {
		if (!connection.isEnabled()) {
			return connection;
		}
		
		// TODO: introduce low probability of randomising rather than perturbing
		
		double newWeight = connection.getWeight() + (2 * random.nextDouble() - 1) * CONNECTION_WEIGHT_MUTATION_STEP;
		
		return new ConnectionGene(connection.getInput(), connection.getOutput(), newWeight, connection.isEnabled(),
			connection.getInnovation());
	}
}