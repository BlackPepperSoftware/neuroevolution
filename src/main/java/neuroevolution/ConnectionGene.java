package neuroevolution;

import java.util.Objects;
import java.util.Random;

public class ConnectionGene implements Gene
{
	private static final double WEIGHT_MUTATION_STEP = 0.1;
	
	private static final double DOUBLE_EQUALS_DELTA = 0.000001;
	
	private final double weight;
	
	private final int innovation;
	
	ConnectionGene(double weight, int innovation)
	{
		this.weight = weight;
		this.innovation = innovation;
	}
	
	private ConnectionGene(ConnectionGene that)
	{
		weight = that.weight;
		innovation = that.innovation;
	}
	
	public double getWeight()
	{
		return weight;
	}
	
	public ConnectionGene withWeight(double weight)
	{
		return new ConnectionGene(weight, innovation);
	}
	
	public int getInnovation()
	{
		return innovation;
	}
	
	public ConnectionGene mutateConnectionWeight(Random random)
	{
		// TODO: introduce low probability of randomising rather than perturbing
		
		double resultWeight = weight + (2 * random.nextDouble() - 1) * WEIGHT_MUTATION_STEP;
		
		return withWeight(resultWeight);
	}
	
	@Override
	public ConnectionGene copy()
	{
		return new ConnectionGene(this);
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(weight, innovation);
	}
	
	@Override
	public boolean equals(Object object)
	{
		if (!(object instanceof ConnectionGene))
		{
			return false;
		}
		
		ConnectionGene that = (ConnectionGene) object;
		
		return Math.abs(weight - that.weight) < DOUBLE_EQUALS_DELTA
			&& innovation == that.innovation;
	}
	
	@Override
	public String toString()
	{
		return String.format("%s[weight=%f, innovation=%d]", getClass().getName(), weight, innovation);
	}
}
